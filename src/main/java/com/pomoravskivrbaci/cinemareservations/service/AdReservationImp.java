package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.UnofficialAdReservation;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.repository.AdReservationRepository;
import com.pomoravskivrbaci.cinemareservations.repository.UnofficialAdReservationRepository;

@Service
public class AdReservationImp implements AdReservationService {

	@Autowired
	private AdReservationRepository repository;

	@Autowired
	private UnofficialAdReservationRepository uarepository;

	@Override
	public void insertOfficalAd(Ad ad, User user) {
		repository.insertOfficialAd(ad,user);
		
	}

	@Override
	public void insertUnofficialAd(int bid, Ad ad, User user,int i) {
		repository.insertUnofficialAd(bid,ad,user,i);
		
	}

	

	@Override
	public List<UnofficialAdReservation> getMyAds(User user) {
		// TODO Auto-generated method stub
		return repository.getMyAds(user);
	}

	

	@Override
	public void AcceptAd(Ad foundedAd) {
		repository.AcceptAd(foundedAd);
		
	}

	

	@Override
	public List<User> getRejectedUsers(Ad foundedAd, User user) {
		// TODO Auto-generated method stub
		return repository.getRejectedUsers(foundedAd,user);
	}

	@Override
	public void save(UnofficialAdReservation unofficialAdReservation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Object> getBidedAds(User loggedUser) {
		
		return repository.getBidedAds(loggedUser);
	}

	@Override
	public void changeBid(int bid, Ad foundedAd, User loggedUser, int i) {
		repository.changeBid(bid,foundedAd,loggedUser);
		
	}

	@Override
	public List<Ad> getMyBidedAds(User loggedUser) {
		// TODO Auto-generated method stub
		return repository.getMyBidedAds(loggedUser);
	}

	
	
	
	
}
