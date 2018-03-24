package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Period;
import com.pomoravskivrbaci.cinemareservations.model.Projection;
import com.pomoravskivrbaci.cinemareservations.repository.ProjectionRepository;

@Service
public class ProjectionServiceImpl implements ProjectionService{

	@Autowired
	private ProjectionRepository projectionRepository;
	@Override
	public List<Projection> findByRepertoires_id(Long id) {
		// TODO Auto-generated method stub
		return projectionRepository.findByRepertoires_id(id);
	}
	@Override
	public Projection findByPeriods(Period period) {
		// TODO Auto-generated method stub
		return projectionRepository.findByPeriods(period);
	}
	@Override
	public Projection findById(Long id) {
		// TODO Auto-generated method stub
		return projectionRepository.findOne(id);
	}
	

}
