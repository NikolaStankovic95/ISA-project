package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.AdStatus;
import com.pomoravskivrbaci.cinemareservations.model.FanZone;
import com.pomoravskivrbaci.cinemareservations.repository.AdRepository;
import com.pomoravskivrbaci.cinemareservations.repository.FanZoneRepository;

@Service
public class AdServiceImp implements AdService {

	@Autowired
	private AdRepository adRepository;
	
	@Autowired
	private FanZoneRepository fanZoneRepository;
	
	@Override
	public List<Ad> getAdByFanZoneId(Long id) {
		FanZone fz = fanZoneRepository.findById(1L);
		return adRepository.getAdByFanZoneId(fz);
	}

	@Override
	public Ad createAd(Ad ad) {
		return adRepository.save(ad);
	}

	@Override
	public List<Ad> getInitAds() {
		return adRepository.getInitAds();
	}

	@Override
	public Ad findById(Long id) {
		return adRepository.findOne(id);
		
	}

	@Override
	@Transactional
	public void update(AdStatus status, Long id) {
		adRepository.update(status, id);
		
	}

	@Override
	public List<Ad> getOfficalAds(long l) {
		FanZone fz = fanZoneRepository.findById(1L);
		return adRepository.getOfficialAds(fz);
	}

	@Override
	public List<Ad> getUnofficalAds(long l) {
		FanZone fz = fanZoneRepository.findById(1L);
		return adRepository.getUnofficialAds(fz);
	}

	@Override
	@Transactional
	public void updateQuantity(int i, Long id) {
		adRepository.updateQuantity(i,id);
		
	}

}
