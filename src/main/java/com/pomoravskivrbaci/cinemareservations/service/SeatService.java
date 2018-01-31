package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Seat;

public interface SeatService {

	List<Seat> findByHallId(Long id);
}
