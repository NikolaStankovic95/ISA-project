package com.pomoravskivrbaci.cinemareservations.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Friendship implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;


	@Column(name="accepted")
	protected boolean accepted;

	@ManyToOne
	protected User user;
	
	
	@ManyToOne
	protected User friend;
	
	public User getFriend() {
		return friend;
	}

	public Friendship(){}
	public Friendship(Long id, User user, User friend, boolean accepted) {
		super();
		this.id = id;
		this.user = user;
		this.friend = friend;
		this.accepted = accepted;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	
	
}
