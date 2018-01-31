package com.pomoravskivrbaci.cinemareservations.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.pomoravskivrbaci.cinemareservations.model.Period;
import com.pomoravskivrbaci.cinemareservations.model.Projection;
import com.pomoravskivrbaci.cinemareservations.model.Seat;
import com.pomoravskivrbaci.cinemareservations.service.HallService;
import com.pomoravskivrbaci.cinemareservations.service.InstitutionService;
import com.pomoravskivrbaci.cinemareservations.service.PeriodService;
import com.pomoravskivrbaci.cinemareservations.service.ProjectionService;
import com.pomoravskivrbaci.cinemareservations.service.SeatService;


@Controller
@RequestMapping("/reservationController")
public class ReservationController {

	@Autowired
	private InstitutionService institutionService;
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private PeriodService periodService;
	@Autowired
	private ProjectionService projectionService;

	
	@Autowired
	private HallService hallService;
	
	@RequestMapping("/getCinemas")
	private @ResponseBody List<Institution> findCinemas(){
		
		List<Institution> listOfCinemas=institutionService.findByType(InstitutionType.CINEMA);
		System.out.println(listOfCinemas.size());
		return listOfCinemas;
	}
	
	@RequestMapping("/getProjections/{id}")
	private @ResponseBody List<Projection> findProjections(@PathVariable("id") String id){
		
		List<Projection> projections=projectionService.findByRepertoires_id(Long.parseLong(id));
		System.out.println(projections.size());
		
		return projections;
	}

	@RequestMapping("/getProjectionsPeriod/{id}")
	private @ResponseBody List<Period> findProjectionsPeriod(@PathVariable("id") String id){
		
		List<Period> periods=periodService.findByProjectionId(Long.parseLong(id));
		System.out.println(periods.size());
		
		return periods;
	}

	@RequestMapping("/getProjectionHalls/{id}")
	private @ResponseBody List<Hall> findHalls(@PathVariable("id") String id){
		
		List<Hall> halls=hallService.findByProjection_id(Long.parseLong(id));
		System.out.println(halls.size());
		
		return halls;
	}
	
	@RequestMapping("/getCinemasHall/{id}")
	private @ResponseBody List<Hall> findCinemasHall(@PathVariable("id") String id){
		
		List<Hall> listOfCinemasHall=hallService.findByInstitutionId(Long.parseLong(id));
		System.out.println(listOfCinemasHall.size());
		return listOfCinemasHall;
	}
	
	@RequestMapping("/getHallSeats/{id}")
	private @ResponseBody List<Seat> findHallSeats(@PathVariable("id") String id){
		
		List<Seat> listOfSeats=seatService.findByHallId(Long.parseLong(id));
		System.out.println(listOfSeats.size());
		return listOfSeats;
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