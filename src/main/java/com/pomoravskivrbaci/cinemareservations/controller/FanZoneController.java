package com.pomoravskivrbaci.cinemareservations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pomoravskivrbaci.cinemareservations.model.FanZone;
import com.pomoravskivrbaci.cinemareservations.service.FanZoneService;

@Controller
@RequestMapping("/fanZoneController")
public class FanZoneController {

	@Autowired
	private FanZoneService fanZoneService;

	@RequestMapping("/getFanzone")
	private ResponseEntity<FanZone> getFanZone() {
		FanZone fz = fanZoneService.findFanZoneById(1L);
		System.out.println(fz);
		return new ResponseEntity<FanZone>(fz, HttpStatus.OK);
	}
	
}

