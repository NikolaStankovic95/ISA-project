package com.pomoravskivrbaci.cinemareservations.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pomoravskivrbaci.cinemareservations.messaging.Producer;
import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.HallSegment;
import com.pomoravskivrbaci.cinemareservations.model.Institution;
import com.pomoravskivrbaci.cinemareservations.model.InstitutionType;
import com.pomoravskivrbaci.cinemareservations.model.Period;
import com.pomoravskivrbaci.cinemareservations.model.Projection;
import com.pomoravskivrbaci.cinemareservations.model.Reservation;
import com.pomoravskivrbaci.cinemareservations.model.Seat;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.service.EmailService;
import com.pomoravskivrbaci.cinemareservations.service.HallSegmentService;
import com.pomoravskivrbaci.cinemareservations.service.HallService;
import com.pomoravskivrbaci.cinemareservations.service.InstitutionService;
import com.pomoravskivrbaci.cinemareservations.service.PeriodService;
import com.pomoravskivrbaci.cinemareservations.service.ProjectionService;
import com.pomoravskivrbaci.cinemareservations.service.ReservationService;
import com.pomoravskivrbaci.cinemareservations.service.SeatService;


@Controller
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private InstitutionService institutionService;
	
	@Autowired
	Producer producer;
	
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private PeriodService periodService;
	@Autowired
	private ProjectionService projectionService;

	
	@Autowired
	private HallService hallService;
	
	@Autowired
	private HallSegmentService hallSegmentService;
	@RequestMapping("/cinemaReservation")
	public String cinema(HttpServletRequest request){
		List<Institution> listOfCinemas=institutionService.findByType(InstitutionType.CINEMA);
		request.setAttribute("institutions", listOfCinemas);
		return "forward:/cinema.jsp";
	}
	@RequestMapping("/theatreReservation")
	public String theathre(HttpServletRequest request){
		List<Institution> listOfTheatres=institutionService.findByType(InstitutionType.THEATRE);
		request.setAttribute("institutions", listOfTheatres);
		return "forward:/cinema.jsp";

	}
	@RequestMapping("/getCinemas")
	private @ResponseBody List<Institution> findCinemas(){
		
		List<Institution> listOfCinemas=institutionService.findByType(InstitutionType.CINEMA);
		System.out.println(listOfCinemas.size());
		return listOfCinemas;
	}
	
	@RequestMapping("/getProjections/{id}/{date}")
	private @ResponseBody List<Projection> findProjections(@PathVariable("id") String id,@PathVariable("date") String date){
		List<Projection> onThisDate=new ArrayList<Projection>();
		
		if(id!="" && date!=""){
		List<Projection> projections=projectionService.findByRepertoires_id(Long.parseLong(id));
		for(Projection projection:projections){
			for(Period period:projection.getPeriods()){
				if(period.getDate().toString().split(" ")[0].equals(date)){
					onThisDate.add(projection);
					break;
				}
			}
		}
		}
		return onThisDate;
	}

	@RequestMapping("/getProjectionsPeriod/{id}")
	private @ResponseBody List<Period> findProjectionsPeriod(@PathVariable("id") String id){
		List<Period> periods=new ArrayList<Period>();
		if(id!="" || id!=null){
			periods=periodService.findByProjectionId(Long.parseLong(id));
			for(Period item:periods){
				Timestamp stamp = new Timestamp(item.getDate().getTime());
				Date date = new Date(stamp.getTime());
				item.setDate(date);
				}
		}
		return periods;
	}

	@RequestMapping("/getProjectionHalls/{id}")
	private @ResponseBody List<Hall> findHalls(@PathVariable("id") String id){
		List<Hall> halls=new ArrayList<Hall>();
		if(id!="" || id!=null){
			halls=hallService.findByProjection_id(Long.parseLong(id));
		}
		return halls;
	}
	
	@RequestMapping("/getCinemasHall/{id}")
	private @ResponseBody List<Hall> findCinemasHall(@PathVariable("id") String id){
		List<Hall> listOfCinemasHall=new ArrayList<Hall>();
		if(id!="" && id!=null)
			listOfCinemasHall=hallService.findByInstitutionId(Long.parseLong(id));
		return listOfCinemasHall;
	}
	
	
	
	@RequestMapping("/getCinemaByName/{name}")
	private ResponseEntity<Institution> findCinemaByName(@PathVariable("name") String name){
		
		Institution cinema=institutionService.findByName(name);
		return new ResponseEntity<Institution>(cinema,HttpStatus.OK);
	}

	@RequestMapping("/findHallById/{id}")
	private ResponseEntity<Hall> findByHallId(@PathVariable("id") Long id){
		
		Hall hall=hallService.findById(id);
		return new ResponseEntity<Hall>(hall,HttpStatus.OK);
	}
	
	@RequestMapping("/findProjectionById/{id}")
	private ResponseEntity<Projection> findProjectionById(@PathVariable("id") Long id){
		
		Projection projection=projectionService.findById(id);
		return new ResponseEntity<Projection>(projection,HttpStatus.OK);
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
	
	@RequestMapping(value="/getHallSeats",	method=RequestMethod.POST,	
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody List<HallSegment> getHallSeats(@RequestBody Object object){
		List<HallSegment> hallSegments=new ArrayList<HallSegment>();
		
		String [] IDs=object.toString().substring(1,object.toString().length()-1).split(",");
		if(IDs.length==4){
		List<Reservation> allReservations=reservationService.findByInstitutionIdAndHallIdAndPeriodIdAndProjectionId(
				Long.parseLong(IDs[0].split("=")[1]), Long.parseLong(IDs[1].split("=")[1]), Long.parseLong(IDs[2].split("=")[1]),Long.parseLong(IDs[3].split("=")[1]));
		hallSegments=hallSegmentService.findHallSegmentByHallId(Long.parseLong(IDs[1].split("=")[1]));
		for(Reservation reservation:allReservations){
			for(HallSegment segment:hallSegments){
				for(Seat seat:segment.getSeats()){
					if(seat.getId().equals(reservation.getSeat().getId())){
						seat.setFree(true);
					}
				}
			}
		}
		}
		return hallSegments;
	}
	
	@RequestMapping(value="/getReservedSeats/{seats}/{id}")
	private ResponseEntity<List<Seat>> getReservedSeats(@PathVariable("seats") String seats,@PathVariable ("id") Long id){
		List<HallSegment> hallSegments=hallSegmentService.findHallSegmentByHallId(id);
		List<Seat> seatForReservation=new ArrayList<Seat>();
		List<Seat> allSeats=new ArrayList<Seat>();
		
		String [] seatsID=seats.split(",");
		for(int i=0;i<seatsID.length;i++){
			allSeats.add(seatService.findById(Long.parseLong(seatsID[i])));
		}
		for(HallSegment segment:hallSegments){
			for(Seat seat:segment.getSeats()){
				for(Seat reservationSeat:allSeats){
					if(seat.getId().equals(reservationSeat.getId())){
						seatForReservation.add(seat);
					}
				}
			}
		}
		
		return new ResponseEntity<List<Seat>>(seatForReservation,HttpStatus.OK);
	}
	@RequestMapping(value="/getSelectedPeriod/{id}")
	private ResponseEntity<Period> getSelectedPeriod(@PathVariable("id") Long id){
		Period period=periodService.findById(id);
		return new ResponseEntity<Period>(period,HttpStatus.OK);
	}
	@RequestMapping(value="/makeReservation/{invite}",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	private synchronized ResponseEntity<Object>  makeReservation(@PathVariable ("invite") String invite,@RequestBody Reservation reservation,HttpServletRequest request){
		User loggedUser=(User)request.getSession().getAttribute("loggedUser");
		List<Reservation> reservations=reservationService.findAll();
		boolean contains=reservations.stream().anyMatch(item->item.getInstitution().getId().equals(reservation.getInstitution().getId()) &&
				item.getHall().getId().equals(reservation.getHall().getId()) &&
				item.getPeriod().getId().equals(reservation.getPeriod().getId()) &&
				item.getProjection().getId().equals(reservation.getProjection().getId()) &&
				item.getSeat().getId().equals(reservation.getSeat().getId()));
		System.out.println(contains);
		if(contains==false){
			if(invite.equals("true")){
				try {
					reservation.setHallSegment(reservation.getSeats().getHallSegment());
					reservationService.save(reservation);
					
					emailService.inviteFriend(reservation.getInvited(), loggedUser, reservation);
					return new ResponseEntity<>(reservation,HttpStatus.OK);
				} catch (MailException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}else{
				reservation.setHallSegment(reservation.getSeats().getHallSegment());
				
				reservation.setAccepted(true);
				emailService.notifyOwner(reservation.getOwner(),reservation);
				reservationService.save(reservation);
				return new ResponseEntity<>(reservation,HttpStatus.OK);
			}
		}else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value="/send/{topic}",method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public void  sender(@PathVariable String topic, @RequestBody Reservation reservation){
		producer.sendMessageTo(topic,reservation.getSeats().getId());
		
	}
	
	
	
}