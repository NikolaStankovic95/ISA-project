package com.pomoravskivrbaci.cinemareservations.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.pomoravskivrbaci.cinemareservations.model.UserRole;
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
	private String findAllUsers(HttpServletRequest request) {

		List<User> users = userService.findAll();
		System.out.println(users.size());
		request.setAttribute("users", users);
		return "forward:/user_profile.jsp";

	}

	@RequestMapping("/loggedUser")
	private ResponseEntity<User> findLoggedUser(HttpServletRequest request) {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		System.out.println(loggedUser.getEmail());
		return new ResponseEntity<User>(loggedUser, HttpStatus.OK);
	}

	@RequestMapping("/userID/{id}")
	private ResponseEntity<User> findUserByID(HttpServletRequest request, @PathVariable("id") Long id) {
		System.out.println(id);
		User userProfile = userService.findUserById(id);
		return new ResponseEntity<User>(userProfile, HttpStatus.OK);

	}

	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	private String findUserByEmail(HttpServletRequest request, @PathVariable("id") Long id) {

		User userProfile = userService.findUserById(id);
		List<User> users = userService.findAll();
		users.remove(userProfile);
		List<Friendship> sentRequests = userService.findFriends(userProfile, false);
		for (Friendship i : sentRequests) {
			users.remove(i.getUser());
		}
		List<User> notFriends = loggedUserFriends(userProfile, users);
		request.setAttribute("notFriends", notFriends);

		request.setAttribute("user", userProfile);
		return "forward:/user_profile.jsp";

	}

	@RequestMapping(value="/notFriends",method=RequestMethod.GET)
	private ResponseEntity<List<User>> notFriends(HttpServletRequest request) {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		if(loggedUser!=null){
		loggedUser = userService.findUserById(loggedUser.getId());
		List<User> users = userService.findAll();
		users.remove(loggedUser);
		List<Friendship> sentRequests = userService.findFriends(loggedUser, false);
		for (Friendship i : sentRequests) {
			users.remove(i.getUser());
		}
		List<User> notFriends = loggedUserFriends(loggedUser, users);
		request.getSession().setAttribute("loggedUser", loggedUser);
		return new ResponseEntity<List<User>>(notFriends, HttpStatus.OK);

		}
		return null;
		
	}

	private List<User> loggedUserFriends(User userProfile, List<User> users) {
		// TODO Auto-generated method stub
		List<User> friends = new ArrayList<User>();

		for (Friendship item : userProfile.getFriendships()) {
			friends.add(item.getFriend());
		}
		List<User> notFriends = new ArrayList<User>();
		if (friends.size() != 0) {
			for (User item1 : users) {

				boolean contains = false;
				for (User item : friends) {
					if (item.getId() == (item1.getId())) {
						contains = true;
						break;
					}
				}
				if (contains == false)
					notFriends.add(item1);

			}
			return notFriends;
		} else {
			return users;
		}

	}

	/*
	 * @RequestMapping(value="/updateUser", method = RequestMethod.POST,
	 * consumes = MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<User>
	 * update(@RequestBody User user,HttpServletRequest request){ User
	 * loggedUser=(User)request.getSession().getAttribute("loggedUser");
	 * loggedUser.setName(user.getName()); User u=userService.update(loggedUser,
	 * loggedUser.getId()); System.out.println(u.getName());
	 * 
	 * return new ResponseEntity<User>(u, HttpStatus.OK); }
	 */
	
	@RequestMapping(value = "/checkFriendRequest")
	public ResponseEntity<Friendship> checkFriendRequest(HttpServletRequest request, @RequestBody String userProfile) {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		User friend = userService.findUserById(Long.parseLong(userProfile));
		Friendship friendship = friendshipService.findByUserAndFriend(friend, loggedUser);
		if (friendship != null) {
			return new ResponseEntity<Friendship>(friendship, HttpStatus.OK);

		} else
			return new ResponseEntity<Friendship>(new Friendship(), HttpStatus.OK);

	}

	@RequestMapping(value = "/findUserFriends")
	public ResponseEntity<List<Friendship>> findUserFriends(HttpServletRequest request) {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		List<Friendship> userFriends = userService.findFriendRequests(loggedUser, true);
		request.setAttribute("friends", userFriends);
		return new ResponseEntity<List<Friendship>>(userFriends, HttpStatus.OK);

	}

	@RequestMapping(value = "/findUserFriendRequests")
	public ResponseEntity<List<Friendship>> findUserFriendRequests(HttpServletRequest request) {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		List<Friendship> userFriends = userService.findFriendRequests(loggedUser, false);
		return new ResponseEntity<List<Friendship>>(userFriends, HttpStatus.OK);

	}

	@RequestMapping(value = "/deleteFriend")
	public ResponseEntity<List<Friendship>> deleteFriend(HttpServletRequest request, @RequestBody String email) {
		User user = (User) request.getSession().getAttribute("loggedUser");
		user=userService.findUserByEmail(user.getEmail());
		User friend = (User) userService.findUserByEmail(email);
		Friendship userFriendship = friendshipService.findByUserAndFriend(user, friend);
		Friendship friendFriendship = friendshipService.findByUserAndFriend(friend, user);

		friendshipService.deleteById(userFriendship.getId());
		friendshipService.deleteById(friendFriendship.getId());
		deleteUserFriend(user, userFriendship);
		deleteUserFriend(friend, friendFriendship);

		User userReceived = userService.update(friend, friend.getId());
		User userSent = userService.update(user, user.getId());

		List<Friendship> userFriends = userService.findFriendRequests(user, true);
		request.getSession().setAttribute("loggedUser", user);
		
		return new ResponseEntity<List<Friendship>>(userFriends, HttpStatus.OK);
	}

	private void deleteUserFriend(User user, Friendship friendship) {
		Friendship forDelete = null;
		for (Friendship f : user.getFriendships()) {
			if (f.getId().equals(friendship.getId())) {
				forDelete = f;
			}
		}
		if (forDelete != null) {
			user.getFriendships().remove(forDelete);
			friendshipService.deleteById(forDelete.getId());
		}
	}

	@RequestMapping(value = "/addFriend")
	public ResponseEntity<List<Friendship>> addFriend(HttpServletRequest request, @RequestBody String email) {
		User user = (User) request.getSession().getAttribute("loggedUser");
		User friend = (User) userService.findUserByEmail(email);
		Friendship userFriendship = friendshipService.findByUserAndFriend(user, friend);
		userFriendship.setAccepted(true);
		friendshipService.update(userFriendship, userFriendship.getId());

		Friendship friendship = new Friendship();
		friendship.setAccepted(true);
		friendship.setFriend(user);
		friendship.setUser(friend);
		friend.getFriendships().add(friendship);
		friend.getFriendships().add(friendship);

		friend.setFriendships(friend.getFriendships());

		User userReceived = userService.update(friend, friend.getId());
		request.getSession().setAttribute("loggedUser", user);
		
		List<Friendship> userFriends = userService.findFriendRequests(user, true);
		return new ResponseEntity<List<Friendship>>(userFriends, HttpStatus.OK);
	}

	@RequestMapping(value = "/sendFriendRequest/{friendId}")
	private ResponseEntity<User> sendFriendRequest(HttpServletRequest request,
			@PathVariable("friendId") String friendId) {
		User user = (User) request.getSession().getAttribute("loggedUser");

		User friend = userService.findUserById(Long.parseLong(friendId));
		// --------------
		Friendship friendshipFriend = new Friendship();
		friendshipFriend.setFriend(user);
		friendshipFriend.setUser(friend);
		friendshipFriend.setAccepted(false);
		friend.getFriendships().add(friendshipFriend);

		friend.setFriendships(friend.getFriendships());
		// ------------------------

		User userReceived = userService.update(friend, Long.parseLong(friendId));
		request.getSession().setAttribute("loggedUser", user);
		
		if (userReceived != null) {
			System.out.println(userReceived.getFriendships().size());

			return new ResponseEntity<User>(userReceived, HttpStatus.OK);
		} else
			return null;
	}
		
	@RequestMapping(value="/searchUser",method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<User>> searchUsers(@RequestBody User user,HttpServletRequest request){
		User logged = (User) request.getSession().getAttribute("loggedUser");

		User userProfile = userService.findUserById(logged.getId());
		List<User> users = userService.findAll();
		users.remove(userProfile);
		List<Friendship> sentRequests = userService.findFriends(userProfile, false);
		for (Friendship i : sentRequests) {
			users.remove(i.getUser());
		}
		List<User> notFriends = loggedUserFriends(userProfile, users);
		List<User> getFriends=new ArrayList<User>();
		if(user.getName()==""){
			List<User> founded=userService.findUserBySurnameIgnorableCaseContaining(user.getSurname());
			for(User u:notFriends){
				for(User item:founded){
					if(u.getId()==item.getId()){
						getFriends.add(item);
					}
				}
			}
			return new ResponseEntity<List<User>>(getFriends,HttpStatus.OK);
		}
		else if(user.getSurname()==""){
			List<User> founded=userService.findUserByNameIgnorableCaseContaining(user.getName());
			for(User u:notFriends){
				for(User item:founded){
					if(u.getId()==item.getId()){
						getFriends.add(item);
					}
				}
			}
			return new ResponseEntity<List<User>>(getFriends,HttpStatus.OK);
		}
		else{
			List<User> founded=userService.findUserByNameAndSurnameIgnorableCaseContaining(user.getName(), user.getSurname());
			for(User u:notFriends){
				for(User item:founded){
					if(u.getId()==item.getId()){
						getFriends.add(item);
					}
				}
			}
			return new ResponseEntity<List<User>>(getFriends,HttpStatus.OK);
		
		}
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		request.setAttribute("loggedUser", null);
		return "redirect:/Login.html";
	}

	@RequestMapping(value="/changeRole", method = RequestMethod.PATCH)
	public ResponseEntity<User> changeRole(@RequestBody String str){
		String[] params = str.split("#");
		Long id = Long.parseLong(params[0]);
		User user = userService.findUserById(id);
		user.setRole(UserRole.valueOf(params[1]));
		
		userService.createUser(user);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}
