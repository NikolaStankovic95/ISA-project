package com.pomoravskivrbaci.cinemareservations.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pomoravskivrbaci.cinemareservations.model.Friendship;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.model.UserRole;
import com.pomoravskivrbaci.cinemareservations.service.EmailService;
import com.pomoravskivrbaci.cinemareservations.service.UserService;

@Controller
@RequestMapping("/registrationController")
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage() {
		return "redirect:/Registration.jsp";
	}
	
	@RequestMapping(value="/login")
	private ResponseEntity<Object> login(@RequestBody User user,HttpServletRequest request) throws URISyntaxException{
    User loggedUser=userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
    URI uri = null;
		if(loggedUser!=null){
			 
			if(loggedUser.isActivated()){
				 uri=new URI("/userController/user/"+loggedUser.getId());
				
				 HttpHeaders httpHeaders = new HttpHeaders();
				
				httpHeaders.setLocation(uri);
				request.getSession().setAttribute("loggedUser", loggedUser);
				return new ResponseEntity<>(uri, HttpStatus.OK);
			}
			else {
				 uri=new URI("/Login.jsp");
				return  new ResponseEntity<>(uri, HttpStatus.BAD_REQUEST);
				}
		}else {
			
			 uri=new URI("/Login.jsp");
			return  new ResponseEntity<>(uri, HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Aktivacija korisničkog naloga
	 * @param id Korisnikov email
	 * @return stranica na koju se redirektuje
	 */
	
	@RequestMapping(value="/activation/{id}")
	private String activateAccount(@PathVariable String id){
		User user=userService.findUserById(Long.parseLong(id));
		if(user!=null){
			
		int flag=userService.setFixedActivatedFor(true, user.getId());
		return "redirect:/Login.jsp";
		
		}else {
			return "redirect:/Login.jsp";
		}
		
	}
	
	@RequestMapping(value="/register",		
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Object> registrateUser(
			@Validated @RequestBody User user,Errors errors){
		if (errors.hasErrors()) {
			return new ResponseEntity<>(errors.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
		}
		for(User item:userService.findAll()){
			if(item.getEmail().equals(user.getEmail())){
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				
			}
		}
		user.setRole(UserRole.USER);
		user.setActivated(false);
		user.setFirstlogin(false);
		user.setFriendships(new ArrayList<Friendship>());
		User addedUser=userService.createUser(user);
		
		try {
			emailService.sendNotificaitionAsync(addedUser);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(addedUser, HttpStatus.OK);
	}
	@RequestMapping(value = "/changePass", method = RequestMethod.POST)
	private String chagePass(
			@RequestParam(value = "oldPass", required = true) String oldPass,
			@RequestParam(value = "newPass", required = true) String newPass,
			@RequestParam(value = "newPassRepeat", required = true) String newPassRepeat,
			HttpSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		System.out.println("Pogodio");
		if (loggedUser.getPassword().equals(oldPass)) {
			loggedUser.setFirstlogin(false);
			userService.setPasswordFor(newPass, loggedUser.getId());
			loggedUser = userService.findUserById(loggedUser.getId());
			session.setAttribute("loggedUser",loggedUser );
		}
		return "forward : Cinema.html";
	}
	
	@RequestMapping(value = "/getLoggedUser", method = RequestMethod.GET)
	private ResponseEntity<User> getLoggedUser(HttpSession session){
		User loggedUser = (User) session.getAttribute("loggedUser");
		return new ResponseEntity<User>(loggedUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/changeUser", method = RequestMethod.POST)
	private ResponseEntity<User> changeUser(@RequestBody User user,HttpSession session){
		User loggedUser = (User) session.getAttribute("loggedUser");
		userService.updateUser(loggedUser.getId(),user.getEmail(),user.getCity(),user.getName(),user.getSurname(),user.getNumber());
		loggedUser = userService.findUserById(loggedUser.getId());
		session.setAttribute("loggedUser",loggedUser );
		return new ResponseEntity<User>(loggedUser, HttpStatus.OK);
	}
}
