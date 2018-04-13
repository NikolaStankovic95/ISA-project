package com.pomoravskivrbaci.cinemareservations.testing.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Institution;
import com.pomoravskivrbaci.cinemareservations.model.Period;
import com.pomoravskivrbaci.cinemareservations.model.Projection;
import com.pomoravskivrbaci.cinemareservations.model.Reservation;
import com.pomoravskivrbaci.cinemareservations.model.Seat;
import com.pomoravskivrbaci.cinemareservations.service.HallService;
import com.pomoravskivrbaci.cinemareservations.service.InstitutionService;
import com.pomoravskivrbaci.cinemareservations.service.PeriodService;
import com.pomoravskivrbaci.cinemareservations.service.ProjectionService;
import com.pomoravskivrbaci.cinemareservations.service.ReservationService;
import com.pomoravskivrbaci.cinemareservations.service.SeatService;

import static com.pomoravskivrbaci.cinemareservations.constants.UserConstants.ID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTesting {

	@Autowired
	private ReservationService reservationService;
	
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
	
	@Test
	public void findAll(){
		List<Reservation> reservations=reservationService.findAll();
		assertNotNull(reservations);
		assertNotEquals(reservations.size(),0);
		
	} 
	@Test
	public void findById(){
		Reservation reservation=reservationService.findById(1L);
		assertNotNull(reservation);
		
	}
	@Test
	@Transactional
	@Rollback(true)
	public void delete(){
		int dbSizeBeforeRemove = reservationService.findAll().size();
		reservationService.delete(1L);
		
		List<Reservation> reservations = reservationService.findAll();
		assertThat(reservations).hasSize(dbSizeBeforeRemove - 1);
		
		Reservation dbStudent = reservationService.findById(1L);
		assertThat(dbStudent).isNull();

		
	}
	@Test
	public void findByOwnerId(){
		List<Reservation> reservations=reservationService.findByOwnerId(ID);
		assertNotNull(reservations);
		assertNotEquals(reservations.size(),0);
		
	} 
	@Test
	public void findByInstitutionIdAndHallIdAndPeriodIdAndProjectionId(){
		List<Reservation> reservations=reservationService.findByInstitutionIdAndHallIdAndPeriodIdAndProjectionId(1L,1L,1L,1L);
		assertNotNull(reservations);
		assertNotEquals(reservations.size(),0);
		
	} 
	@Test
	@Transactional
	@Rollback(true)
	public void saveReservation(){
		Institution inst=institutionService.findById(1L);
		Hall hall=hallService.findById(1L);
		Projection projection=projectionService.findById(1L);
		Period period=periodService.findById(1L);
		Seat seat=seatService.findById(1L);
		Reservation reservation=new Reservation(1L,projection,hall,seat,period,inst,null);
		Reservation saved=reservationService.save(reservation);
		assertNotNull(saved);
		assertEquals(saved.getId(),reservation.getId());
	}
}
