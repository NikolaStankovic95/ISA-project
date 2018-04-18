package com.pomoravskivrbaci.cinemareservations.controller;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.pomoravskivrbaci.cinemareservations.model.*;
import com.pomoravskivrbaci.cinemareservations.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//github.com/jova1111/ISA-project.git

@Controller
@RequestMapping("/institution")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @Autowired

    private FanZoneService fanzoneService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private InstitutionRatingService institutionRatingService;

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH)
    private ResponseEntity editInstitutionDescription(@PathVariable("id")Long id, @RequestBody Institution inst) {
        institutionService.setInstitutionInfoById(id, inst.getName(), inst.getAddress(), inst.getDescription());
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value="/create", method = RequestMethod.GET)
    private String create(HttpServletRequest request) {
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
    	return  "forward:/createinstitution.jsp";
    }
    @RequestMapping(value="/createInstitution", method = RequestMethod.POST)
    private ResponseEntity<Institution> createInstitution(@RequestBody Institution inst) {
        System.out.println("Pogodio");
    	institutionService.saveOrUpdate(inst);
    	FanZone fz = new FanZone(inst.getId(),inst.getName()+" Fanzone ");
    	fanzoneService.save(fz);
        return new ResponseEntity<Institution>(inst,HttpStatus.OK);
    }
    
    @RequestMapping(value="/users", method = RequestMethod.GET)
    private String users(HttpServletRequest request) {
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
    	return  "forward:/allusers.jsp";
    }
    @RequestMapping(value="/insertFanzoneAdmins/{id}", method = RequestMethod.PATCH)
    private ResponseEntity<FanZone> insertFanzoneAdmins(@RequestBody List<Long> ids,@PathVariable("id") Long fzid){
    	FanZone fz = fanzoneService.findFanZoneById(fzid);
    	
    	for(Long id : ids){
    		System.out.println("id: "+id);
    		User user = userService.findUserById(id);
    		fz.getFanZoneAdmins().add(user);
    	}
    	fanzoneService.save(fz);
    	return new ResponseEntity<FanZone>(fz,HttpStatus.OK);
    }
    
    @RequestMapping(value="/insertInstitutionAdmins/{id}", method = RequestMethod.PATCH)
    private ResponseEntity<Institution> insertInstitutionAdmins(@RequestBody List<Long> ids,@PathVariable("id") Long instid){
    	Institution inst = institutionService.findById(instid);
    	
    	for(Long id : ids){
    		System.out.println("id: "+id);
    		User user = userService.findUserById(id);
    		inst.getAdmins().add(user);
    	}
    	institutionService.saveOrUpdate(inst);
    	return new ResponseEntity<Institution>(inst,HttpStatus.OK);
    }
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    private String showInstitutionProfile(@PathVariable("id")Long id, HttpServletRequest request) {
        Institution institution = institutionService.findById(id);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();
        List<Reservation> fastReservations = reservationService.findAll().stream()
            .filter(reservation -> (reservation.getInstitution().getId().equals(id)) && (reservation.getOwner() == null) && (reservation.getPeriod().getDate().after(yesterday)))
            .collect(Collectors.toList());
        request.setAttribute("institution", institution);
        request.setAttribute("fastReservations", fastReservations);
        return "forward:/institution_profile.jsp";
    }

    @RequestMapping(value="/{id}/rate", method = RequestMethod.POST)
    private ResponseEntity rate(@PathVariable("id")Long id, @RequestBody InstitutionRating institutionRating, HttpSession session) {
        User loggedUser = (User)session.getAttribute("loggedUser");
        if(loggedUser == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        for(InstitutionRating instRating : loggedUser.getInstitutionRatings()) {
            if(instRating.getInstitution().getId().equals(id)) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        Institution institution = institutionService.findById(id);
        institutionRating.setInstitution(institution);
        institution.addRating(institutionRating);
        institutionRating.setUser(loggedUser);
        loggedUser.addInstitutionRating(institutionRating);
        institutionRatingService.saveOrUpdate(institutionRating);
        return new ResponseEntity(HttpStatus.OK);
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
