
package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.AdStatus;
import com.pomoravskivrbaci.cinemareservations.model.User;

public interface AdService {

	List<Ad> getAdByFanZoneId(Long id);
	List<Ad> getInitAds(User user);
	Ad createAd(Ad ad);
	Ad findById(Long id);
	void update(AdStatus status,Long id);
	List<Ad> getOfficalAds(long l);
	List<Ad> getUnofficalAds(long l);
	void updateQuantity(int i, Long id);
	void delete(Ad foundedAd);
	void updateAd(String name, String description, int quantity, Long id);
	
}
