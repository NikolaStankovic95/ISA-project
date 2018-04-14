package com.pomoravskivrbaci.cinemareservations.testing.controller;

import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.CITY;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.EMAIL;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.ID;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.NAME;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.PASSWORD;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.PHONE;
import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.SURNAME;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.pomoravskivrbaci.cinemareservations.TestUtil;
import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Institution;
import com.pomoravskivrbaci.cinemareservations.model.Period;
import com.pomoravskivrbaci.cinemareservations.model.Projection;
import com.pomoravskivrbaci.cinemareservations.model.Reservation;
import com.pomoravskivrbaci.cinemareservations.model.Seat;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.model.UserRole;
import com.pomoravskivrbaci.cinemareservations.service.HallService;
import com.pomoravskivrbaci.cinemareservations.service.InstitutionService;
import com.pomoravskivrbaci.cinemareservations.service.PeriodService;
import com.pomoravskivrbaci.cinemareservations.service.ProjectionService;
import com.pomoravskivrbaci.cinemareservations.service.SeatService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationControllerTesting {

	private static final String URL="/reservation";
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private PeriodService periodService;
	@Autowired
	private ProjectionService projectionService;
	@Autowired
	private SeatService seatService;
	@Autowired
	private InstitutionService institutionService;
	
	
	@Autowired
	private HallService hallService;
	
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void cinemaReservation()throws Exception{
		this.mockMvc.perform(get(URL+"/cinemaReservation")).andExpect(view().name("forward:/cinema.jsp"));
	}
	@Test
	public void theatreReservation()throws Exception{
		this.mockMvc.perform(get(URL+"/theatreReservation")).andExpect(view().name("forward:/cinema.jsp"));
	}
	@Test
	public void cinemasHomepage()throws Exception{
		this.mockMvc.perform(get(URL+"/cinemas")).andExpect(view().name("forward:/cinema_homepage.jsp"));
	}
	@Test
	public void theatresHomepage()throws Exception{
		this.mockMvc.perform(get(URL+"/theatres")).andExpect(view().name("forward:/theatre_homepage.jsp"));
	}
	@Test
	public void getCinemas()throws Exception{
		this.mockMvc.perform(get(URL+"/getCinemas").contentType(contentType)).andExpect(status().isOk()).andExpect(jsonPath("$",hasSize(1)));
	}
	@Test
	public void findProjectionsPeriod()throws Exception{
		this.mockMvc.perform(get(URL+"/getProjectionsPeriod/1").contentType(contentType)).andExpect(status().isOk()).andExpect(jsonPath("$",hasSize(2)));
	}
	@Test
	public void findHalls()throws Exception{
		this.mockMvc.perform(get(URL+"/getProjectionHalls/1").contentType(contentType)).andExpect(status().isOk()).andExpect(jsonPath("$",hasSize(2)));
	}
	@Test
	public void findCinemaByName()throws Exception{
		this.mockMvc.perform(get(URL+"/getCinemaByName/CineStar Novi Sad").contentType(contentType)).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("CineStar Novi Sad"));
	}
	
	@Test
	public void findHallById()throws Exception{
		this.mockMvc.perform(get(URL+"/findHallById/1").contentType(contentType)).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Sala 1"));
	}
	@Test 
	public void findProjectionById()throws Exception{
		this.mockMvc.perform(get(URL+"/findProjectionById/1").contentType(contentType)).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Rambo 3"));
	}
	@Test 
	public void getSelectedPeriod()throws Exception{
		this.mockMvc.perform(get(URL+"/getSelectedPeriod/1").contentType(contentType)).andExpect(status().isOk());
	}
	@Test 
	public void getReservedSeats()throws Exception{
		this.mockMvc.perform(get(URL+"/getReservedSeats/1,2,3/1").contentType(contentType)).andExpect(status().isOk()).andExpect(jsonPath("$",hasSize(3)));
	}
	@Test 
	@Transactional
	@Rollback(true)
	public void makeReservation()throws Exception{
		User user=new User(ID,EMAIL,PASSWORD,NAME,
    			SURNAME,CITY,false,true,UserRole.USER,PHONE);
		Institution inst=institutionService.findById(1L);
		Hall hall=hallService.findById(1L);
		Projection projection=projectionService.findById(1L);
		Period period=periodService.findById(1L);
		Seat seat=seatService.findById(1L);
		Reservation reservation=new Reservation(1L,projection,hall,seat,period,inst,user);
		 MockHttpSession mockHttpSession = new MockHttpSession(); 
		 mockHttpSession.setAttribute("loggedUser", user);
		String json = TestUtil.json(reservation);
			
		this.mockMvc.perform(post(URL+"/makeReservation/false").contentType(contentType).content(json)).andExpect(status().isOk());
	}
}
