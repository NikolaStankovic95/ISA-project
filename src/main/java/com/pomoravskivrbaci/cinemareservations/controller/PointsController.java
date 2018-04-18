package com.pomoravskivrbaci.cinemareservations.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pomoravskivrbaci.cinemareservations.model.Points;
import com.pomoravskivrbaci.cinemareservations.service.PointsService;

@Controller
@RequestMapping(value="/points")
public class PointsController {

	@Autowired
	private PointsService pointsService;
	
	@RequestMapping(value="/get")
	private String getPoints(HttpServletRequest request) {
		Points points = pointsService.getPointsById(1L);
		request.setAttribute("points", points);
		return "forward:/points.jsp";
	}
	@RequestMapping(value="/change")
	private ResponseEntity<Points> change(@RequestBody Points points) {
		pointsService.save(points);
		
		return  new ResponseEntity<Points>(points,HttpStatus.OK);
	}
}
