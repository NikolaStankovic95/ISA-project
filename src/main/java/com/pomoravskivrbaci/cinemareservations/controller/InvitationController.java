package com.pomoravskivrbaci.cinemareservations.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pomoravskivrbaci.cinemareservations.model.Reservation;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.service.ReservationService;


@Controller
@RequestMapping("/invitationController")
public class InvitationController {

	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping("/invitation/{id}")
	private String invitiation(@PathVariable ("id") Long id,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("loggedUser");
		Reservation reservation=reservationService.findById(id);
		request.setAttribute("user", user);
		if(user.getId()==reservation.getInvited().getId() && reservation.isAccepted()==false){
			
			request.setAttribute("reservation",reservation);
			
		
		}else{
			
			request.setAttribute("reservation",null);
		}
		return "forward:/invitation.jsp";
	}
	@RequestMapping(value="/accept/{id}",method=RequestMethod.GET)
	private String accept(HttpServletRequest request,@PathVariable ("id") Long id){
		User user=(User) request.getSession().getAttribute("loggedUser");
		Reservation reservation=reservationService.findById(id);
		reservation.setAccepted(true);
		reservation.setOwner(user);
		reservationService.update(user,reservation.getId());
		request.setAttribute("reservation",null);
		return "redirect:/userController/user/"+user.getId();
	}
	
	@RequestMapping(value="/reject/{id}",method=RequestMethod.GET)
	private String reject(HttpServletRequest request,@PathVariable ("id") Long id){
		User user=(User) request.getSession().getAttribute("loggedUser");
		reservationService.delete(id);
		request.setAttribute("reservation",null);
		return "redirect:/userController/user/"+user.getId();
	}
}
