package com.pomoravskivrbaci.cinemareservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.repository.AdReservationRepository;

@Service
public class AdReservationImp implements AdReservationService {

	@Autowired
	private AdReservationRepository repository;

	@Override
	public void insertOfficalAd(Ad ad, User user) {
		repository.insertOfficialAd(ad,user);
		
	}

	@Override
	public void insertUnofficialAd(int bid, Ad ad, User user) {
		repository.insertUnofficialAd(bid,ad,user);
		
	}
	
	
}
