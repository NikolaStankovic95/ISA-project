package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.AdStatus;

public interface AdService {

	List<Ad> getAdByFanZoneId(Long id);
	List<Ad> getInitAds();
	Ad createAd(Ad ad);
	Ad findById(Long id);
	void update(AdStatus status,Long id);
	List<Ad> getOfficalAds(long l);
	List<Ad> getUnofficalAds(long l);
	void updateQuantity(int i, Long id);
	
}
