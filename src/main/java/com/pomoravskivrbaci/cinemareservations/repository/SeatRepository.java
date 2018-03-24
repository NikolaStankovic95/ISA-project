package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.pomoravskivrbaci.cinemareservations.model.Seat;

public interface SeatRepository extends Repository<Seat,Long>{

	List<Seat> findByHallId(Long id);
	Seat findById(Long id);
}
