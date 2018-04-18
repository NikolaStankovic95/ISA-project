package com.pomoravskivrbaci.cinemareservations.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pomoravskivrbaci.cinemareservations.model.FanZone;
import com.pomoravskivrbaci.cinemareservations.service.FanZoneService;

@Controller
@RequestMapping("/fanzone")
public class FanZoneController {

	@Autowired
	private FanZoneService fanZoneService;

	@RequestMapping(value = "/{id}",  method = RequestMethod.GET)
	private String redirect(@PathVariable("id") Long id, HttpServletRequest request) {
		FanZone fz = fanZoneService.findFanZoneById(id);
		request.setAttribute("fanzone", fz);
		return "forward:/fanzone.jsp";
	}
	
	@RequestMapping(value = "/createofficial",  method = RequestMethod.GET)
	private String redirectOfficial() {
		System.out.println("pogodio");
		return "forward:/addOfficialAd.jsp";
	}
	
	@RequestMapping(value = "/createunofficial",  method = RequestMethod.GET)
	private String redirectUnofficial() {
		return "forward:/addUnofficialAd.jsp";
	}
}

