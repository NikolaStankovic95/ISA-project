package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.FanZone;
import com.pomoravskivrbaci.cinemareservations.model.InstitutionType;


@Repository
public interface FanZoneRepository extends PagingAndSortingRepository<FanZone,Long> {
	
	
	@Query("select r from FanZone r where r.id = ?1")
    FanZone findById(Long id);
	
	
}
