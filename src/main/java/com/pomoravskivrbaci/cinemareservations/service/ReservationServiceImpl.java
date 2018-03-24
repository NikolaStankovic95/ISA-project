package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomoravskivrbaci.cinemareservations.model.Reservation;
import com.pomoravskivrbaci.cinemareservations.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public List<Reservation> findAll() {
		// TODO Auto-generated method stub
		return reservationRepository.findAll();
	}

	@Override
	public Reservation save(Reservation reservation) {
		// TODO Auto-generated method stub
		return reservationRepository.save(reservation);
	}

	@Override
	public List<Reservation> findByInstitutionIdAndHallIdAndPeriodIdAndProjectionId(Long instID, Long hallID,
			Long periodID, Long projectionID) {
		// TODO Auto-generated method stub
		return reservationRepository.findByInstitutionIdAndHallIdAndPeriodIdAndProjectionId(instID, hallID, periodID, projectionID);
	}

}
