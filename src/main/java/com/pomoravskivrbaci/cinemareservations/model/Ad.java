package com.pomoravskivrbaci.cinemareservations.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Ad implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@Column(nullable = false)
	protected String name;
	@Column(nullable = true)
	protected String description;
	//dodaj datum i sliku
	
	@ManyToOne(optional = false)
	private FanZone fanZone;
	
	public FanZone getFanZone() {
		return fanZone;
	}
	public void setFanZone(FanZone fanZone) {
		this.fanZone = fanZone;
	}
	@Column(nullable = false)
	protected AdStatus adStatus;
	
	@Column(nullable = false)
	protected Date expirationDate;
	/*
	@Column(nullable = true)
	protected byte[] image;
	*/
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public AdStatus getAdStatus() {
		return adStatus;
	}
	public void setAdStatus(AdStatus adStatus) {
		this.adStatus = adStatus;
	}
	public Ad(){
		
	}
	
	public Ad(String name, String description,
			AdStatus adStatus, Date expirationDate) {
		super();
		
		this.name = name;
		this.description = description;
		
		this.adStatus = adStatus;
		this.expirationDate = expirationDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/*public void setImage(byte[] blob) {
		this.image = blob;
		
	}*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
