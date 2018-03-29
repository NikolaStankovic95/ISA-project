package com.pomoravskivrbaci.cinemareservations.service;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.User;

public interface AdReservationService {

	

	void insertOfficalAd(Ad ad, User user);

	void insertUnofficialAd(int bid, Ad foundedAd, User loggedUser);

}
