package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pomoravskivrbaci.cinemareservations.model.Reservation;


public interface ReservationRepository extends PagingAndSortingRepository<Reservation,Long>{

	List<Reservation> findAll();
	List<Reservation> findByInstitutionIdAndHallIdAndPeriodIdAndProjectionId(Long instID,Long hallID,Long periodID,Long projectionID);
} 
