package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Projection;


public interface ProjectionService {

	List<Projection> findByRepertoires_id(Long id);

}
