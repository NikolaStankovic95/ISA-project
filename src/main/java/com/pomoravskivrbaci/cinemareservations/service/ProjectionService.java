package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Period;
import com.pomoravskivrbaci.cinemareservations.model.Projection;


public interface ProjectionService {

	Projection findById(Long id);
	List<Projection> findByRepertoires_id(Long id);
	Projection findByPeriods(Period period);
}
