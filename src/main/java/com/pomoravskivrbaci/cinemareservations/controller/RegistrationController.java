package com.pomoravskivrbaci.cinemareservations.controller;

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

import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.service.EmailService;
import com.pomoravskivrbaci.cinemareservations.service.UserService;

@Controller
@RequestMapping("/userController")
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage() {
		return "Registration";
	}
	@RequestMapping(value="/login")
	private ResponseEntity<User> login(@RequestBody User user){
		
		User loggedUser=userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
		if(loggedUser!=null){
			System.out.println(loggedUser.isActivated());
			if(loggedUser.isActivated())
			return new ResponseEntity<User>(user, HttpStatus.OK);
			else 
				return null;
		}else 
			return null;
	}
	
	/**
	 * Aktivacija korisničkog naloga
	 * @param email Korisnikov email
	 * @return stranica na koju se redirektuje
	 */
	
	@RequestMapping(value="/activation/{email}")
	private String activateAccount(@PathVariable String email){
		User user=userService.findUserByEmail("nemanja.gavrilovic1995@gmail.com");
		if(user!=null){
			
		int flag=userService.setFixedActivatedFor(true, user.getEmail());
		System.out.println(flag);
			if(flag==1)
			return "Cinema.html";
			else
				return "Login.html";
		}else {
			return "Login.html";
		}
	
		
	}
	
	@RequestMapping(value="/register",		
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<User> registrateUser(
			@RequestBody User user){
		System.out.println(user);
		user.setActivated(false);
		user.setFirstlogin(false);
		
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
