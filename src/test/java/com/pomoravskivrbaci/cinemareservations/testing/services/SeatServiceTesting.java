package com.pomoravskivrbaci.cinemareservations.testing.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pomoravskivrbaci.cinemareservations.model.Seat;
import com.pomoravskivrbaci.cinemareservations.service.SeatService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeatServiceTesting {

	@Autowired
	private SeatService seatService;
	
	@Test
	public void findById(){
		Seat seat=seatService.findById(1L);
		assertNotNull(seat);
	}
}
