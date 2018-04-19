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
import org.springframework.web.bind.annotation.ResponseBody;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.AdStatus;
import com.pomoravskivrbaci.cinemareservations.model.AdType;
import com.pomoravskivrbaci.cinemareservations.model.FanZone;
import com.pomoravskivrbaci.cinemareservations.model.UnofficialAdReservation;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.service.AdReservationService;
import com.pomoravskivrbaci.cinemareservations.service.AdService;
import com.pomoravskivrbaci.cinemareservations.service.EmailService;
import com.pomoravskivrbaci.cinemareservations.service.FanZoneService;
import com.pomoravskivrbaci.cinemareservations.service.PointsService;
import com.pomoravskivrbaci.cinemareservations.service.UserService;

@Controller
@RequestMapping(value = "/adController")
public class AdController {

	@Autowired
	private AdService adService;

	@Autowired
	private FanZoneService fanZoneService;

	@Autowired
	private AdReservationService adReservationService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserService userService;

	@Autowired
	private PointsService pointsService;
	
	
	@RequestMapping(value = "/getAd/{id}", method = RequestMethod.GET)
	private ResponseEntity <Ad> getAd(@PathVariable("id") Long id) {

		Ad ad = adService.findById(id);

		return new ResponseEntity <Ad>(ad,HttpStatus.OK);
	}

	@RequestMapping(value = "/getOfficialAds/{id}", method = RequestMethod.GET)
	private @ResponseBody List<Ad> getOfficialAds(@PathVariable("id") Long id) {

		List<Ad> ads = adService.getOfficalAds(id);

		return ads;
	}

	@RequestMapping(value = "/getUnofficialAds/{id}", method = RequestMethod.GET)
	private @ResponseBody List<Ad> getUnofficialAds(
			@PathVariable("id") Long id, HttpServletRequest request) {
		List<Ad> ads = adService.getUnofficalAds(id);
		User loggedUser = (User) request.getSession()
				.getAttribute("loggedUser");
		
		List<Ad> bidedAds = adReservationService.getMyBidedAds(loggedUser);
		
		for(Ad ad : bidedAds){
			if(ads.contains(ad)){
				ads.remove(ad);
			}
		}
		return ads;
	}

	@RequestMapping(value = "/addOfficialAd/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Ad> addOfficialAd(@RequestBody Ad ad,
			@PathVariable("id") Long id, HttpServletRequest request)
			throws IOException {
		FanZone fz = fanZoneService.findFanZoneById(id);
		User loggedUser = (User) request.getSession()
				.getAttribute("loggedUser");
		System.out.println("logged user: " + loggedUser);
		int flag=0;
		for(User u : fz.getFanZoneAdmins()){
			System.out.println("admin: " +u);
			if(u.getEmail().equals(loggedUser.getEmail())){
				flag=1;
				break;
			}
		}
		if(flag != 1){
			return new ResponseEntity<Ad>(ad, HttpStatus.BAD_REQUEST);

		}
		
		ad.setPublisher(loggedUser);
		ad.setFanZone(fz);
		ad.setAdStatus(AdStatus.INIT);
		ad.setAdType(AdType.OFFICIAL);
		ad.setExpirationDate(new Date(1L));

		System.out.println("pogodio");

		Ad addedAd = adService.createAd(ad);

		return new ResponseEntity<Ad>(addedAd, HttpStatus.OK);
	}

	@RequestMapping(value = "/addUnofficialAd/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Ad> addUnofficialAd(@RequestBody Ad ad,
			@PathVariable("id") Long id, HttpServletRequest request)
			throws IOException {
		FanZone fz = fanZoneService.findFanZoneById(id);
		User loggedUser = (User) request.getSession()
				.getAttribute("loggedUser");
		if(loggedUser ==null){
			return new ResponseEntity<Ad>(ad, HttpStatus.UNAUTHORIZED);
		}
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
	private @ResponseBody List<Ad> getInitAds(HttpServletRequest request) {
		User loggedUser = (User) request.getSession()
				.getAttribute("loggedUser");
		List<Ad> ads = adService.getInitAds(loggedUser);

		return ads;
	}

	@RequestMapping(value = "/approveAd/{id}", method = RequestMethod.PATCH)
	private ResponseEntity<Ad> approveAd(@RequestBody Ad ad,@PathVariable Long id) {
		Ad foundedAd = adService.findById(id);
		// foundedAd.setAdStatus(AdStatus.APPROVED);
		System.out.println("Verzija nadjene: " +ad.getVersion()+ " Verzija poslate: "+ foundedAd.getVersion());
		if(foundedAd.getVersion() != ad.getVersion()){
			return new ResponseEntity<Ad>(foundedAd, HttpStatus.UNAUTHORIZED);
		}
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
	private ResponseEntity<Ad> reserveAd(@PathVariable Long id,
			HttpServletRequest request) {
		Ad foundedAd = adService.findById(id);
		User loggedUser = (User) request.getSession()
				.getAttribute("loggedUser");
		if (loggedUser == null) {
			return new ResponseEntity<Ad>(foundedAd, HttpStatus.UNAUTHORIZED);
		}

		adService.updateQuantity(foundedAd.getQuantity() - 1, id);
		foundedAd = adService.findById(id);
		adReservationService.insertOfficalAd(foundedAd, loggedUser);
		loggedUser.setPoints(loggedUser.getPoints()+pointsService.getPointsById(1L).getAdReserved());
		userService.createUser(loggedUser);
		
		return new ResponseEntity<Ad>(foundedAd, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteAd/{id}", method = RequestMethod.POST)
	private ResponseEntity<Ad> deleteAd(@PathVariable Long id,
			HttpServletRequest request) {
		Ad foundedAd = adService.findById(id);
		
		 User loggedUser = (User) request.getSession()
		 .getAttribute("loggedUser"); if (loggedUser == null) { return new
		 ResponseEntity<Ad>(foundedAd, HttpStatus.UNAUTHORIZED); }
		 
		 if(!foundedAd.getPublisher().getEmail().equals(loggedUser.getEmail())){
			 { return new
					 ResponseEntity<Ad>(foundedAd, HttpStatus.UNAUTHORIZED); }
		 }
		adService.delete(foundedAd);

		return new ResponseEntity<Ad>(foundedAd, HttpStatus.OK);
	}

	@RequestMapping(value = "/makeBid", method = RequestMethod.POST)
	private ResponseEntity<Ad> makeBid(@RequestBody String info,
			HttpServletRequest request) {

		String[] str = info.split("#");
		int bid = Integer.parseInt(str[0]);
		Long id = Long.parseLong(str[1], 10);
		Ad foundedAd = adService.findById(id);
		User loggedUser = (User) request.getSession()
				.getAttribute("loggedUser");
		if (loggedUser == null) {
			return new ResponseEntity<Ad>(foundedAd, HttpStatus.UNAUTHORIZED);
		}
		foundedAd = adService.findById(id);
		// adReservationService.save(new UnofficialAdReservation(bid, foundedAd,
		// loggedUser,0);
		adReservationService.insertUnofficialAd(bid, foundedAd, loggedUser, 0);
		return new ResponseEntity<Ad>(foundedAd, HttpStatus.OK);
	}

	@RequestMapping(value = "/getMyAds", method = RequestMethod.GET)
	private ResponseEntity<List<UnofficialAdReservation>> getMyAds(
			HttpServletRequest request) {

		User loggedUser = (User) request.getSession()
				.getAttribute("loggedUser");
		List<UnofficialAdReservation> myAds = adReservationService
				.getMyAds(loggedUser);
		if (loggedUser == null) {
			return new ResponseEntity<List<UnofficialAdReservation>>(myAds,
					HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<List<UnofficialAdReservation>>(myAds,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/notifyUsers", method = RequestMethod.PATCH)
	private ResponseEntity<Ad> notifyUsers(@RequestBody String info) {
		String[] str = info.split("#");
		Long adId = Long.parseLong(str[0], 10);
		Long userId = Long.parseLong(str[1], 10);
		Ad foundedAd = adService.findById(adId);
		adReservationService.AcceptAd(foundedAd);
		User user = userService.findUserById(userId);
		emailService.notifyAcceptedBider(user,foundedAd);

		List<User> rejected = adReservationService.getRejectedUsers(foundedAd,
				user);
		emailService.notifyRefussedBider(rejected,foundedAd);
		user.setPoints(user.getPoints()+pointsService.getPointsById(1L).getAdReserved());
		userService.createUser(user);
		
		return new ResponseEntity<Ad>(foundedAd, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/getBidedAds", method = RequestMethod.GET)
	private ResponseEntity<List<Object>> getBidedAds(HttpServletRequest request) {

		User loggedUser = (User) request.getSession()
				.getAttribute("loggedUser");
		List<Object> myAds = adReservationService.getBidedAds(loggedUser);

		request.setAttribute("ads", myAds);
		return new ResponseEntity<List<Object>>(myAds, HttpStatus.OK);
	}

	@RequestMapping(value = "/changeBid", method = RequestMethod.POST)
	private ResponseEntity<Ad> changeBid(@RequestBody String info,
			HttpServletRequest request) {

		String[] str = info.split("#");
		int bid = Integer.parseInt(str[0]);
		Long id = Long.parseLong(str[1], 10);
		Ad foundedAd = adService.findById(id);
		User loggedUser = (User) request.getSession()
				.getAttribute("loggedUser");
		if (loggedUser == null) {
			return new ResponseEntity<Ad>(foundedAd, HttpStatus.UNAUTHORIZED);
		}
		foundedAd = adService.findById(id);
		// adReservationService.save(new UnofficialAdReservation(bid, foundedAd,
		// loggedUser,0);
		adReservationService.changeBid(bid, foundedAd, loggedUser, 0);
		return new ResponseEntity<Ad>(foundedAd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/changeAd/{id}", method = RequestMethod.GET)
	private String changeAd(@PathVariable("id") Long id,HttpServletRequest request){
		System.out.println("POGIO GAAA");
		Ad ad = adService.findById(id);
		User loggedUser = (User) request.getSession()
				.getAttribute("loggedUser");
		if (loggedUser == null) {
			return  "forward:/Login.jsp";
		}
		if(!ad.getPublisher().getEmail().equals(loggedUser.getEmail())){
			return "forward:/Login.jsp";
		}
		request.setAttribute("ad", ad);
		return "forward:/changeAd.jsp";
	}
	
	@RequestMapping(value = "changeAdValue/{id}", method = RequestMethod.PATCH)
	private ResponseEntity<Ad> changeAdValue(@RequestBody Ad ad,@PathVariable("id") Long id,HttpServletRequest request){
		System.out.println(ad.getName()+ad.getDescription()+ad.getQuantity());
		adService.updateAd(ad.getName(), ad.getDescription(), ad.getQuantity(), id);
		return new ResponseEntity<Ad>(ad,HttpStatus.OK);
	}
}
