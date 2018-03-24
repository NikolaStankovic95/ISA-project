package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomoravskivrbaci.cinemareservations.model.Period;
import com.pomoravskivrbaci.cinemareservations.repository.PeriodRepository;

@Service
public class PeriodServiceImpl implements PeriodService {

	@Autowired
	private PeriodRepository periodRepository;
	@Override
	public List<Period> findByProjectionId(Long id) {
		// TODO Auto-generated method stub
		return periodRepository.findByProjectionId(id);
	}
	@Override
	public Period findById(Long id) {
		// TODO Auto-generated method stub
		return periodRepository.findById(id);
	}
	
}
