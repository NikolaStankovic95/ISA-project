package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Reservation;
import com.pomoravskivrbaci.cinemareservations.model.User;

public interface ReservationService {

	List<Reservation> findAll();
	Reservation save(Reservation reservation);
	List<Reservation> findByInstitutionIdAndHallIdAndPeriodIdAndProjectionId(Long instID,Long hallID,Long periodID,Long projectionID);
	Reservation findById(Long id);
	void update(User u, Long id);
	void delete(Long id);
}
