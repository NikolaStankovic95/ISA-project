package com.pomoravskivrbaci.cinemareservations.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pomoravskivrbaci.cinemareservations.model.Friendship;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.service.FriendshipService;
import com.pomoravskivrbaci.cinemareservations.service.UserService;

@Controller
@RequestMapping("/userController")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private FriendshipService friendshipService;
	
	@RequestMapping("/users")
	private String findAllUsers(HttpServletRequest request){
		
		List<User> users=userService.findAll();
		System.out.println(users.size());
		request.setAttribute("users", users);
		return "forward:/user_profile.jsp";
		
	}
	
	@RequestMapping("/user/{email}")
	private String findUserByEmail(HttpServletRequest request,@PathVariable("email") String email){
		
		User userProfile=userService.findUserById(Long.parseLong(email));
		request.setAttribute("user", userProfile);
		return "forward:/user_profile.jsp";
		
	}
	
	
	/*@RequestMapping(value="/updateUser",	
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> update(@RequestBody User user,HttpServletRequest request){
		User loggedUser=(User)request.getSession().getAttribute("loggedUser");
		loggedUser.setName(user.getName());
		User u=userService.update(loggedUser, loggedUser.getId());
		System.out.println(u.getName());
		
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}*/
	
	@RequestMapping(value="/findUserNotFriendWith")
	public ResponseEntity<List<User>> findUserNotFriendWith(HttpServletRequest request){
		User loggedUser=(User)request.getSession().getAttribute("loggedUser");
		List<User> allUsers=userService.findAllUsersExceptLogged(loggedUser.getId());
		List<Friendship> allFriends=userService.findFriends(loggedUser, true);
		List<User> notFriend=new ArrayList<User>();
		if(allFriends.size()==0){
			return  new ResponseEntity<List<User>>(allUsers,HttpStatus.OK);
		}
		else{
			for(User user:allUsers){
				boolean isFriend=false;
				for(Friendship friendship:allFriends){
					if(friendship.getUser().getEmail().equals(user.getEmail())){
						isFriend=true;
					}
				}
				if(isFriend==false){
					notFriend.add(user);
					
				}
			}
			request.getSession().setAttribute("users", notFriend);
			return  new ResponseEntity<List<User>>(notFriend,HttpStatus.OK);
			
		}
	}
	
	@RequestMapping(value="/checkFriendRequest")
	public ResponseEntity<Friendship>  checkFriendRequest(HttpServletRequest request,@RequestBody String userProfile){
		User loggedUser=(User)request.getSession().getAttribute("loggedUser");
		User friend=userService.findUserById(Long.parseLong(userProfile));
		Friendship friendship=friendshipService.findByUserAndFriend(friend, loggedUser);
		Friendship userfriendship=friendshipService.findByUserAndFriend(loggedUser,friend);
		if(userfriendship!=null){
			return  new ResponseEntity<Friendship>(userfriendship,HttpStatus.OK);
				
		}
		else if(friendship!=null){
			return  new ResponseEntity<Friendship>(friendship,HttpStatus.OK);
				
		}
		else
		return  new ResponseEntity<Friendship>(new Friendship(),HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/findUserFriends")
	public ResponseEntity<List<Friendship>> findUserFriends(HttpServletRequest request){
		User loggedUser=(User)request.getSession().getAttribute("loggedUser");
		List<Friendship> userFriends=userService.findFriends(loggedUser,true);
		request.setAttribute("friends", userFriends);
		return new ResponseEntity<List<Friendship>>(userFriends,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/findUserFriendRequests")
	public ResponseEntity<List<Friendship>> findUserFriendRequests(HttpServletRequest request){
		User loggedUser=(User)request.getSession().getAttribute("loggedUser");
		List<Friendship> userFriends=userService.findFriendRequests(loggedUser,false);
		return new ResponseEntity<List<Friendship>>(userFriends,HttpStatus.OK);
		
	}
	@RequestMapping(value="/rejectRequest")
	private ResponseEntity<List<Friendship>> rejectFriendRequest(HttpServletRequest request,@RequestBody String email){
		User loggedUser=(User)request.getSession().getAttribute("loggedUser");
		User friend=(User) userService.findUserByEmail(email);
		
		Friendship userFriendship=friendshipService.findByUserAndFriend(loggedUser, friend);
		
		friendshipService.deleteById(userFriendship.getId());
		
		List<Friendship> userFriends=userService.findFriendRequests(loggedUser,false);
		return new ResponseEntity<List<Friendship>>(userFriends,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/deleteFriend")
	public ResponseEntity<List<Friendship>> deleteFriend(HttpServletRequest request,@RequestBody String email){
		User user=(User) request.getSession().getAttribute("loggedUser");
		User friend=(User) userService.findUserByEmail(email);
		Friendship userFriendship=friendshipService.findByUserAndFriend(user, friend);
		Friendship friendFriendship=friendshipService.findByUserAndFriend(friend, user);
		
		friendshipService.deleteById(userFriendship.getId());
		friendshipService.deleteById(friendFriendship.getId());
		deleteUserFriend(user,userFriendship);
		deleteUserFriend(friend,friendFriendship);
		
		
		User userReceived=userService.update(friend,friend.getId());
		User userSent=userService.update(user,user.getId());
		
		List<Friendship> userFriends=userService.findFriends(user,true);
		return new ResponseEntity<List<Friendship>>(userFriends,HttpStatus.OK);
	}
	private void deleteUserFriend(User user,Friendship friendship){
		Friendship forDelete = null;
		for(Friendship f:user.getFriendships()){
			if(f.getId().equals(friendship.getId())){
				forDelete=f;
			}
		}
		if(forDelete!=null)
			user.getFriendships().remove(forDelete);
	}
	@RequestMapping(value="/addFriend")
	public ResponseEntity<List<Friendship>> addFriend(HttpServletRequest request,@RequestBody String email){
		User user=(User) request.getSession().getAttribute("loggedUser");
		User friend=(User) userService.findUserByEmail(email);
		Friendship userFriendship=friendshipService.findByUserAndFriend(user, friend);
		userFriendship.setAccepted(true);
		friendshipService.update(userFriendship,userFriendship.getId());
		
		Friendship friendship=new Friendship();
		friendship.setAccepted(true);
		friendship.setFriend(user);
		friendship.setUser(friend);
		friend.getFriendships().add(friendship);
		friend.getFriendships().add(friendship);
		
		friend.setFriendships(friend.getFriendships());
		User userReceived=userService.update(friend,friend.getId());
		
		
		List<Friendship> userFriends=userService.findFriends(user,true);
		return new ResponseEntity<List<Friendship>>(userFriends,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/sendFriendRequest/{friendId}")
	private ResponseEntity<User> sendFriendRequest(HttpServletRequest request,@PathVariable("friendId") String friendId){
		User user=(User) request.getSession().getAttribute("loggedUser");
		
		User friend=userService.findUserById(Long.parseLong(friendId));
		
		Friendship exsist=friendshipService.findByUserAndFriend(user, friend);
		Friendship exsist1=friendshipService.findByUserAndFriend(friend, user);
		if(exsist==null && exsist1==null){
		//--------------
			Friendship friendshipFriend=new Friendship();
			friendshipFriend.setFriend(user);
			friendshipFriend.setUser(friend);
			friendshipFriend.setAccepted(false);
			friend.getFriendships().add(friendshipFriend);
			
			friend.setFriendships(friend.getFriendships());
			//------------------------
			
			User userReceived=userService.update(friend,Long.parseLong(friendId));
			
			if(userReceived!=null){
				System.out.println(userReceived.getFriendships().size());
				
				return new ResponseEntity<User>(userReceived, HttpStatus.OK);
			}else
				return null;
		}else{
			return null;
		}
	}
	@RequestMapping(value="/searchUser/{name}/{surname}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<User>> searchUsers(HttpServletRequest request,@PathVariable("name") String name,
			@PathVariable("surname") String surname ){
		System.out.println(name);
		System.out.println(surname);
		return null;
	}
}
