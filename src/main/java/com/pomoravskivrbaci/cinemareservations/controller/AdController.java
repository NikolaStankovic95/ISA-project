package com.pomoravskivrbaci.cinemareservations.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.pomoravskivrbaci.cinemareservations.model.AdType;
import com.pomoravskivrbaci.cinemareservations.model.FanZone;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.service.AdReservationService;
import com.pomoravskivrbaci.cinemareservations.service.AdService;
import com.pomoravskivrbaci.cinemareservations.service.FanZoneService;

@Controller
@RequestMapping(value = "/adController")
public class AdController {

	@Autowired
	private AdService adService;

	@Autowired
	private FanZoneService fanZoneService;

	@Autowired
	private AdReservationService AdReservationService;
	@RequestMapping(value = "/getOfficialAds", method = RequestMethod.GET)
	private @ResponseBody
	List<Ad> getOfficialAds() {
		List<Ad> ads = adService.getOfficalAds(1L);

		return ads;
	}

	@RequestMapping(value = "/getUnofficialAds", method = RequestMethod.GET)
	private @ResponseBody
	List<Ad> getUnofficialAds() {
		List<Ad> ads = adService.getUnofficalAds(1L);

		return ads;
	}

	@RequestMapping(value = "/addOfficialAd", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Ad> addOfficialAd(@RequestBody Ad ad, HttpServletRequest request)
			throws IOException {
		FanZone fz = fanZoneService.findFanZoneById(1L);
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		ad.setPublisher(loggedUser);
		ad.setFanZone(fz);
		ad.setAdStatus(AdStatus.INIT);
		ad.setAdType(AdType.OFFICIAL);
		ad.setExpirationDate(new Date(1L));
		
		System.out.println("pogodio");

		Ad addedAd = adService.createAd(ad);

		return new ResponseEntity<Ad>(addedAd, HttpStatus.OK);
	}

	@RequestMapping(value = "/addUnofficialAd", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Ad> addUnofficialAd(@RequestBody Ad ad,HttpServletRequest request)
			throws IOException {
		FanZone fz = fanZoneService.findFanZoneById(1L);
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		ad.setPublisher(loggedUser);
		ad.setFanZone(fz);
		ad.setAdStatus(AdStatus.INIT);
		ad.setAdType(AdType.UNOFFICIAL);
		ad.setQuantity(0);
		
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
	private ResponseEntity<Ad> approveAd(@PathVariable Long id) {
		Ad foundedAd = adService.findById(id);
		// foundedAd.setAdStatus(AdStatus.APPROVED);
		adService.update(AdStatus.APPROVED, id);
		return new ResponseEntity<Ad>(foundedAd, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/refuseAd/{id}", method = RequestMethod.PATCH)
	private ResponseEntity<Ad> refuseAd(@PathVariable Long id) {
		Ad foundedAd = adService.findById(id);
		// foundedAd.setAdStatus(AdStatus.APPROVED);
		adService.update(AdStatus.REFUSED, id);
		return new ResponseEntity<Ad>(foundedAd, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/reserveAd/{id}", method = RequestMethod.POST)
	private ResponseEntity<Ad> reserveAd(@PathVariable Long id, HttpServletRequest request) {
		Ad foundedAd = adService.findById(id);
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		if(loggedUser == null){
			return new ResponseEntity<Ad>(foundedAd, HttpStatus.UNAUTHORIZED);
		}
		
		adService.updateQuantity(foundedAd.getQuantity() - 1, id);
		foundedAd = adService.findById(id);
		AdReservationService.insertOfficalAd(foundedAd,loggedUser);
		
		return new ResponseEntity<Ad>(foundedAd, HttpStatus.OK);
	}
	@RequestMapping(value = "/makeBid", method = RequestMethod.POST)
	private ResponseEntity<Ad> makeBid(
			@RequestBody String info,
			 HttpServletRequest request){
		
		String[] str = info.split("#");
		int bid =Integer.parseInt(str[0]);
		Long id = Long.parseLong(str[1], 10); 
		Ad foundedAd = adService.findById(id);
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		if(loggedUser == null){
			return new ResponseEntity<Ad>(foundedAd, HttpStatus.UNAUTHORIZED);
		}
		foundedAd = adService.findById(id);
		AdReservationService.insertUnofficialAd(bid,foundedAd,loggedUser);
		return new ResponseEntity<Ad>(foundedAd, HttpStatus.OK);
	} 
}
