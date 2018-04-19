package com.pomoravskivrbaci.cinemareservations.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.pomoravskivrbaci.cinemareservations.service.PointsService;
import com.pomoravskivrbaci.cinemareservations.service.UserService;

@Controller
@RequestMapping("/registrationController")
public class RegistrationController {

	@Autowired
	private UserService userService;

	@Autowired
	private PointsService pointsService;

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage() {
		return "redirect:/Registration.jsp";
	}

	@RequestMapping(value = "/login")
	private ResponseEntity<Object> login(@RequestBody User user,
			HttpServletRequest request) throws URISyntaxException {
		User loggedUser = userService.findUserByEmailAndPassword(
				user.getEmail(), user.getPassword());
		URI uri = null;
		if (loggedUser != null) {

			if (loggedUser.isActivated()) {
				if (loggedUser.isFirstlogin()
						&& loggedUser.getRole().equals(UserRole.FANZONEADMIN)) {
					uri = new URI("/registrationController/changePass");

					HttpHeaders httpHeaders = new HttpHeaders();
					request.getSession().setAttribute("id", loggedUser.getId());
					request.getSession().setAttribute("loggedUser", loggedUser);
					
					httpHeaders.setLocation(uri);
					loggedUser.setPoints(loggedUser.getPoints()
							+ pointsService.getPointsById(1L).getLogin());
					userService.createUser(loggedUser);
					return new ResponseEntity<>(uri, HttpStatus.OK);

				}
				if(loggedUser.getRole().equals(UserRole.FANZONEADMIN)){
					uri = new URI("/fanZoneAdmin.jsp");

					HttpHeaders httpHeaders = new HttpHeaders();
					request.getSession().setAttribute("id", loggedUser.getId());
					request.getSession().setAttribute("loggedUser", loggedUser);
					
					httpHeaders.setLocation(uri);
					loggedUser.setPoints(loggedUser.getPoints()
							+ pointsService.getPointsById(1L).getLogin());
					userService.createUser(loggedUser);
					return new ResponseEntity<>(uri, HttpStatus.OK);

				}
				if(loggedUser.getRole().equals(UserRole.SYSADMIN)){
					uri = new URI("/sysadmin.jsp");

					HttpHeaders httpHeaders = new HttpHeaders();
					request.getSession().setAttribute("id", loggedUser.getId());
					request.getSession().setAttribute("loggedUser", loggedUser);
					
					httpHeaders.setLocation(uri);
					loggedUser.setPoints(loggedUser.getPoints()
							+ pointsService.getPointsById(1L).getLogin());
					userService.createUser(loggedUser);
					return new ResponseEntity<>(uri, HttpStatus.OK);

				}
				uri = new URI("/userController/user/" + loggedUser.getId());

				HttpHeaders httpHeaders = new HttpHeaders();

				httpHeaders.setLocation(uri);
				request.getSession().setAttribute("loggedUser", loggedUser);
				loggedUser.setPoints(loggedUser.getPoints()
						+ pointsService.getPointsById(1L).getLogin());
				userService.createUser(loggedUser);
				return new ResponseEntity<>(uri, HttpStatus.OK);
			} else {
				uri = new URI("/Login.jsp");
				return new ResponseEntity<>(uri, HttpStatus.BAD_REQUEST);
			}
		} else {

			uri = new URI("/Login.jsp");
			return new ResponseEntity<>(uri, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Aktivacija korisničkog naloga
	 * 
	 * @param id
	 *            Korisnikov email
	 * @return stranica na koju se redirektuje
	 */

	@RequestMapping(value = "/activation/{id}")
	private String activateAccount(@PathVariable String id) {
		User user = userService.findUserById(Long.parseLong(id));
		if (user != null) {

			int flag = userService.setFixedActivatedFor(true, user.getId());
			return "redirect:/Login.jsp";

		} else {
			return "redirect:/Login.jsp";
		}

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Object> registrateUser(
			@Validated @RequestBody User user, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<>(errors.getAllErrors().toString(),
					HttpStatus.BAD_REQUEST);
		}
		for (User item : userService.findAll()) {
			if (item.getEmail().equals(user.getEmail())) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			}
		}
		user.setRole(UserRole.USER);
		user.setActivated(false);
		user.setFirstlogin(true);
		user.setFriendships(new ArrayList<Friendship>());
		user.setPoints(0);
		User addedUser = userService.createUser(user);

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

	@RequestMapping(value="/changePass")
	private String redirectChangePass(HttpServletRequest request){
		System.out.println(request.getSession().getAttribute("id"));
		return "forward:/changePass.jsp";
	}
	@RequestMapping(value = "/changePass/{id}", method = RequestMethod.POST)
	private ResponseEntity<Object> chagePass(
			@PathVariable("id") Long id,
			@RequestParam(value = "oldPass", required = true) String oldPass,
			@RequestParam(value = "newPass", required = true) String newPass,
			@RequestParam(value = "newPassRepeat", required = true) String newPassRepeat,
			HttpServletRequest request,HttpServletResponse response) throws URISyntaxException {
		User user = userService.findUserById(id);
		System.out.println("Pogodio");
		System.out.println("user: " + user.getEmail() + "pass: "+user.getPassword() +"unesena: "+ oldPass);
		if (user.getPassword().equals(oldPass)) {
			
				user.setFirstlogin(false);
				user.setPassword(newPass);
				userService.createUser(user);
			
			

			
			request.getSession().setAttribute("loggedUser", user);
		
		}
		URI uri = null;
		uri = new URI("/userController/user/" + user.getId());

		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.setLocation(uri);
		
		
		
		userService.createUser(user);
		 try {
			response.sendRedirect(uri.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(uri, HttpStatus.OK);
	}

	@RequestMapping(value = "/getLoggedUser", method = RequestMethod.GET)
	private ResponseEntity<User> getLoggedUser(HttpSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		return new ResponseEntity<User>(loggedUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/changeUser", method = RequestMethod.POST)
	private ResponseEntity<User> changeUser(@RequestBody User user,
			HttpSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		User newUser= userService.findUserByEmail(user.getEmail());
		if(newUser==null){
		userService.updateUser(loggedUser.getId(), user.getEmail(),
				user.getCity(), user.getName(), user.getSurname(),
				user.getNumber());
		loggedUser = userService.findUserById(loggedUser.getId());
		session.setAttribute("loggedUser", loggedUser);
		return new ResponseEntity<User>(loggedUser, HttpStatus.OK);
		}else {
			return new ResponseEntity<User>(loggedUser, HttpStatus.BAD_REQUEST);
		}
	}
}