package com.pomoravskivrbaci.cinemareservations.model;

import com.pomoravskivrbaci.cinemareservations.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;

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
	private int login = 0;
	
	@Column
	private int seatReserved = 0;
	
	@Column
	private int addedFriend = 0;
	
	@Column
	private int adReserved = 0;
	
	@Column
	private int gold = 1000;
	
	@Column
	private int silver = 500;
	
	@Column
	private int bronze = 50;

	
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getSilver() {
		return silver;
	}

	public void setSilver(int silver) {
		this.silver = silver;
	}

	public int getBronze() {
		return bronze;
	}

	public void setBronze(int bronze) {
		this.bronze = bronze;
	}

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
