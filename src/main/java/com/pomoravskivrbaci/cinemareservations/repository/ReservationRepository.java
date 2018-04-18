package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pomoravskivrbaci.cinemareservations.model.Reservation;
import com.pomoravskivrbaci.cinemareservations.model.User;


public interface ReservationRepository extends PagingAndSortingRepository<Reservation,Long>{
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	List<Reservation> findAll();
	List<Reservation> findByInstitutionIdAndHallIdAndPeriodIdAndProjectionId(Long instID,Long hallID,Long periodID,Long projectionID);
	@Modifying
	@Transactional
	@Query("Update Reservation r set r.owner=?1 where r.id=?2")
	void update(User u,Long id);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Transactional
	Reservation save(Reservation reservation);
	List<Reservation> findByOwnerId(Long id);
} 
