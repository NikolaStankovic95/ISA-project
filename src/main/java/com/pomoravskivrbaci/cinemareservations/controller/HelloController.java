package com.pomoravskivrbaci.cinemareservations.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class HelloController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage(HttpServletResponse response) {
		response.setHeader("Content-Type","text/html");
		return "index";
	}

}
