package com.pomoravskivrbaci.cinemareservations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Institution;
import com.pomoravskivrbaci.cinemareservations.model.InstitutionType;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.service.HallService;
import com.pomoravskivrbaci.cinemareservations.service.InstitutionService;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/reservationController")
public class ReservationController {

	@Autowired
	private InstitutionService institutionService;

	@Autowired
	private HallService hallService;
	
	@RequestMapping("/getCinemas")
	private @ResponseBody List<Institution> findCinemas(){
		
		List<Institution> listOfCinemas=institutionService.findByType(InstitutionType.CINEMA);
		System.out.println(listOfCinemas.size());
		return listOfCinemas;
	}
	
	@RequestMapping("/getCinemasHall/{id}")
	private @ResponseBody List<Hall> findCinemasHall(@PathVariable("id") String id){
		
		List<Hall> listOfCinemasHall=hallService.findByInstitutionId(Long.parseLong(id));
		System.out.println(listOfCinemasHall.size());
		return listOfCinemasHall;
	}
	
	@RequestMapping("/getCinemaByName/{name}")
	private ResponseEntity<Institution> findCinemaByName(@PathVariable("name") String name){
		
		Institution cinema=institutionService.findByName(name);
		return new ResponseEntity<Institution>(cinema,HttpStatus.OK);
	}

	@RequestMapping("/cinemas")
	private String cinemasHomepage(HttpServletRequest request) {
		List<Institution> listOfCinemas=institutionService.findByType(InstitutionType.CINEMA);
		request.setAttribute("cinemas", listOfCinemas);
		return "forward:/cinema_homepage.jsp";
	}

	@RequestMapping("/theatres")
	private String theatresHomepage(HttpServletRequest request) {
		List<Institution> listOfTheatres=institutionService.findByType(InstitutionType.THEATRE);
		request.setAttribute("theatres", listOfTheatres);
		return "forward:/theatre_homepage.jsp";
	}
}
