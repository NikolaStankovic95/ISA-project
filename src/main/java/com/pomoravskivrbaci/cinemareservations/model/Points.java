package com.pomoravskivrbaci.cinemareservations.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Points {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private int login;
	
	@Column
	private int seatReserved;
	
	@Column
	private int addedFriend;
	
	@Column
	private int adReserved;
	
	public Points(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getLogin() {
		return login;
	}

	public void setLogin(int login) {
		this.login = login;
	}

	public int getSeatReserved() {
		return seatReserved;
	}

	public void setSeatReserved(int seatReserved) {
		this.seatReserved = seatReserved;
	}

	public int getAddedFriend() {
		return addedFriend;
	}

	public void setAddedFriend(int addedFriend) {
		this.addedFriend = addedFriend;
	}

	public int getAdReserved() {
		return adReserved;
	}

	public void setAdReserved(int adReserved) {
		this.adReserved = adReserved;
	}
	
	
}
