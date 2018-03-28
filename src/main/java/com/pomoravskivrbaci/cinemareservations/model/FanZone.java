package com.pomoravskivrbaci.cinemareservations.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class FanZone implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Column(nullable = false)
	protected String name;
	/*
	@OneToMany
	protected List<User> fanZoneAdmins;
	*/
	public FanZone(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
/*
	public List<User> getFanZoneAdmins() {
		return fanZoneAdmins;
	}

	public void setFanZoneAdmins(List<User> fanZoneAdmins) {
		this.fanZoneAdmins = fanZoneAdmins;
	}
	*/
	
}
