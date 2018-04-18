package com.pomoravskivrbaci.cinemareservations.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pomoravskivrbaci.cinemareservations.model.UnofficialAdReservation;

public interface UnofficialAdReservationRepository extends
		PagingAndSortingRepository<UnofficialAdReservation, Long> {

}
