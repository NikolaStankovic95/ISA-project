package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.UnofficialAdReservation;
import com.pomoravskivrbaci.cinemareservations.model.User;

public interface AdReservationService {

	

	void insertOfficalAd(Ad ad, User user);

	void insertUnofficialAd(int bid, Ad foundedAd, User loggedUser, int i);

	List<UnofficialAdReservation> getMyAds(User user);

	void AcceptAd(Ad foundedAd);

	List<User> getRejectedUsers(Ad foundedAd, User user);

}
