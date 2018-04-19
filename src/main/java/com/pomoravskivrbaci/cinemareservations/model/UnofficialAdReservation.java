package com.pomoravskivrbaci.cinemareservations.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name="unofficial_ad_reservation")
public class UnofficialAdReservation implements Serializable {

	private enum Status{
		INIT,ACCEPTED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(nullable=false,name="bid")
	protected int bid;
	
	public UnofficialAdReservation(){}
	public UnofficialAdReservation(int bid2, Ad foundedAd, User loggedUser,
			int i) {
		this.bid = bid2;
		this.reservedAd = foundedAd;
		this.user = loggedUser;
		this.status = Status.INIT;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	
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
	@OneToOne	
	protected Ad reservedAd;
	@OneToOne
	protected User user;
	
	protected Status status;
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
