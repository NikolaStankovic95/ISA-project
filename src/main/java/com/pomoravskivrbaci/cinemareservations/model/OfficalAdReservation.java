package com.pomoravskivrbaci.cinemareservations.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OfficalAdReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@OneToOne
	protected Ad reservedAd;
	@OneToOne
	protected User user;
	
	public OfficalAdReservation(Ad reservedAd,User user) {
		this.reservedAd = reservedAd;
		this.user = user;
	}

	public void OfficialAdReservation(){}

	public Ad getReservedAd() {
		return reservedAd;
	}

	public void setReservedAd(Ad reservedAd) {
		this.reservedAd = reservedAd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
