package com.pomoravskivrbaci.cinemareservations.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class FanZone implements Serializable {

	@Id
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	protected String name;

	
	 @ManyToMany 
	 protected List<User> fanZoneAdmins;

	public List<User> getFanZoneAdmins() {
		return fanZoneAdmins;
	}

	public void setFanZoneAdmins(List<User> fanZoneAdmins) {
		this.fanZoneAdmins = fanZoneAdmins;
	}

	public FanZone() {

	}

	public FanZone(Long id, String name) {
		this.id = id;
		this.name = name;
        fanZoneAdmins = new ArrayList<User>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/*
	 * public List<User> getFanZoneAdmins() { return fanZoneAdmins; }
	 * 
	 * public void setFanZoneAdmins(List<User> fanZoneAdmins) {
	 * this.fanZoneAdmins = fanZoneAdmins; }
	 */

}
