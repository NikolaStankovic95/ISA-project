package com.pomoravskivrbaci.cinemareservations.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.AdStatus;
import com.pomoravskivrbaci.cinemareservations.model.FanZone;
import com.pomoravskivrbaci.cinemareservations.service.AdService;
import com.pomoravskivrbaci.cinemareservations.service.FanZoneService;

@Controller
@RequestMapping(value = "/adController")
public class AdController {

	@Autowired
	private AdService adService;
	
	@Autowired
	private FanZoneService fanZoneService;
	


	@RequestMapping(value = "/getAds", method = RequestMethod.GET)
	private @ResponseBody
	List<Ad> getAds() {
		List<Ad> ads = adService.getAdByFanZoneId(1L);

		return ads;
	}

	

	@RequestMapping(value = "/addAd", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Ad> adAdd(@RequestBody Ad ad) throws IOException{
		FanZone fz = fanZoneService.findFanZoneById(1L);
		ad.setFanZone(fz);
		ad.setAdStatus(AdStatus.INIT);
		
		
		System.out.println("pogodio");
		
		Ad addedAd = adService.createAd(ad);
		
		return new ResponseEntity<Ad>(addedAd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getInitAds", method = RequestMethod.GET)
	private @ResponseBody
	List<Ad> getInitAds() {
		List<Ad> ads = adService.getInitAds();

		return ads;
	}
	
	@RequestMapping(value = "/approveAd/{id}", method = RequestMethod.PATCH)
	private ResponseEntity<Ad> approveAd(@PathVariable Long id){
		Ad foundedAd = adService.findById(id);
		//foundedAd.setAdStatus(AdStatus.APPROVED);
		adService.update(AdStatus.APPROVED,id);
		return new ResponseEntity<Ad>(foundedAd, HttpStatus.NO_CONTENT);
	}
	@RequestMapping(value = "/refuseAd/{id}", method = RequestMethod.PATCH)
	private ResponseEntity<Ad> refuseAd(@PathVariable Long id){
		Ad foundedAd = adService.findById(id);
		//foundedAd.setAdStatus(AdStatus.APPROVED);
		adService.update(AdStatus.REFUSED,id);
		return new ResponseEntity<Ad>(foundedAd, HttpStatus.NO_CONTENT);
	}
}
