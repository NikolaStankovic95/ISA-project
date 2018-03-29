package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Hall;

public interface HallService {

	List<Hall> findByInstitutionId(Long id);
	Hall findById(Long id);
	List<Hall> findByProjection_id(Long id);
	Hall saveOrUpdate(Hall hall);
}
