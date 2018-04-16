package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.FanZone;
import com.pomoravskivrbaci.cinemareservations.repository.FanZoneRepository;

@Service
public class FanZoneServiceImp implements FanZoneService{

	@Autowired 
	private FanZoneRepository fanZoneRepository;
	@Override
	public FanZone findFanZoneById(Long id) {
		// TODO Auto-generated method stub
		return  fanZoneRepository.findById(id);
	}
	@Override
	public void save(FanZone fanZone) {
		fanZoneRepository.save(fanZone);
		
	}
	
}
