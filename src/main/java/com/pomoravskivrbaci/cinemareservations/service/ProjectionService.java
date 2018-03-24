package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Projection;


public interface ProjectionService {

	Projection findById(Long id);

	List<Projection> findByRepertoires_id(Long id);

	void setProjectionInfoById(Long id, String name, String actors, String genre, String description, String directorName, double rating, Double price);
}
