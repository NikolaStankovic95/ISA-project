package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Period;
import com.pomoravskivrbaci.cinemareservations.model.Projection;


public interface ProjectionService {

	Projection findById(Long id);
	List<Projection> findByRepertoires_id(Long id);
	void setProjectionInfoById(Long id, String name, String actors, String genre, String description, String directorName, double rating, Double price);
	Projection findByPeriods(Period period);
	Projection saveOrUpdate(Projection projection);
	void deleteById(Long id);
}
