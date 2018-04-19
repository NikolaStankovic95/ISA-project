package com.pomoravskivrbaci.cinemareservations.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pomoravskivrbaci.cinemareservations.messaging.Producer;
import com.pomoravskivrbaci.cinemareservations.model.Reservation;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.service.PointsService;
import com.pomoravskivrbaci.cinemareservations.service.ReservationService;
import com.pomoravskivrbaci.cinemareservations.service.UserService;

@Controller
@RequestMapping("/myReservations")
public class MyReservationsController {

	@Autowired
	private UserService userService;
	
	@Autowired
	Producer producer;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private PointsService pointsService;
	@RequestMapping("/")
	private String getMyReservations(HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("loggedUser");
		if(user!=null){
		List<Reservation> reservations=reservationService.findByOwnerId(user.getId());
		List<Reservation> myReservations=new ArrayList<Reservation>();
		Date date=new Date();
		for(Reservation item:reservations){
			if(!date.after(item.getPeriod().getDate()) && item.isAccepted()==true){
				myReservations.add(item);
			}
		}
		request.getSession().setAttribute("reservations", myReservations);
		}
		return "forward:/reservations.jsp";
	}
	@RequestMapping("/delete/{id}")
	private ResponseEntity<List<Reservation>> delete(HttpServletRequest request,@PathVariable ("id") Long id){
		User user=(User) request.getSession().getAttribute("loggedUser");
		
		Reservation reservation=reservationService.findById(id);
		List<Reservation> reservations=(List<Reservation>) request.getSession().getAttribute("reservations");
		Date now = new Date();
		Timestamp stamp = new Timestamp(reservation.getPeriod().getDate().getTime());
		Date date = new Date(stamp.getTime());
		long diffInMillies =(date .getTime() - now.getTime());
		long diffInDays=(date.getDate()-now.getDate());
	    long diffDays = TimeUnit.DAYS.convert(diffInDays, TimeUnit.DAYS);
	    long diffMinutes = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    long diffHours = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    
	    	if(diffDays>=0 && diffMinutes>30 && reservation.getOwner().getId()==user.getId()){
	    		producer.sendMessageTo("reservation",reservation.getSeats().getId());
	    		System.out.println(reservations.size());
	    		reservations.removeIf(item->item.getId().equals(reservation.getId()));
				reservationService.delete(id);
				System.out.println(reservations.size());
				user.setPoints(user.getPoints()
						- pointsService.getPointsById(1L).getSeatReserved());
				userService.update(user, user.getId());
	    	}
	    	else{
	    		return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.BAD_REQUEST);
	    	}
		
		return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.OK);
	}
}
