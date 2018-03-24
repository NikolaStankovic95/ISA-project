package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Reservation;

public interface ReservationService {

	List<Reservation> findAll();
	Reservation save(Reservation reservation);
	List<Reservation> findByInstitutionIdAndHallIdAndPeriodIdAndProjectionId(Long instID,Long hallID,Long periodID,Long projectionID);
}
