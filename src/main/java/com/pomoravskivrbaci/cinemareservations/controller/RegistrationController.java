package com.pomoravskivrbaci.cinemareservations.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		return "redirect:/Registration.html";
	}
	
	@RequestMapping(value="/login")
	private ResponseEntity<User> login(@RequestBody User user,HttpServletRequest request){
    User loggedUser=userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
		if(loggedUser!=null){
		
			if(loggedUser.isActivated()){
				request.getSession().setAttribute("loggedUser", loggedUser);
				return new ResponseEntity<User>(loggedUser, HttpStatus.OK);
			}
			else 
				return null;
		}else 
			return null;
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
		return "redirect:/Login.html";
		
		}else {
			return "redirect:/Login.html";
		}
		
	}
	
	@RequestMapping(value="/register",		
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<User> registrateUser(
			@RequestBody User user){
		
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
		return new ResponseEntity<User>(addedUser, HttpStatus.OK);
	}
}
