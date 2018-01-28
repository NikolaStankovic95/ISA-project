package com.pomoravskivrbaci.cinemareservations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pomoravskivrbaci.cinemareservations.model.Institution;
import com.pomoravskivrbaci.cinemareservations.model.InstitutionType;
import com.pomoravskivrbaci.cinemareservations.service.InstitutionService;



@Controller
@RequestMapping("/reservationController")
public class ReservationController {

	@Autowired
	private InstitutionService institutionService;

	
	@RequestMapping("/getCinema")
	private @ResponseBody List<Institution> findCinemas(){
		
		List<Institution> listOfCinemas=institutionService.findByType(InstitutionType.CINEMA);
		System.out.println(listOfCinemas.size());
		return listOfCinemas;
	}
	
}
