package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.repository.HallRepository;

@Service
public class HallServiceImpl implements HallService{

	@Autowired
	private HallRepository hallRepository;
	@Override
	public List<Hall> findByInstitutionId(Long id) {
		// TODO Auto-generated method stub
		return hallRepository.findByInstitutionId(id);
	}
	@Override
	public List<Hall> findByProjection_id(Long id) {
		// TODO Auto-generated method stub
		return hallRepository.findByProjections_id(id);
	}

	@Override
	public Hall saveOrUpdate(Hall hall) {
		return hallRepository.save(hall);
	}

	@Override
	public Hall findById(Long id) {
		// TODO Auto-generated method stub
		return hallRepository.findById(id);
	}
}
