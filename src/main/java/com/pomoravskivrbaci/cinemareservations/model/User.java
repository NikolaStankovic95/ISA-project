package com.pomoravskivrbaci.cinemareservations.model;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@OneToMany(cascade={ALL},fetch=FetchType.EAGER,mappedBy="user")
	protected List<Friendship> friendships;
	
	@OneToMany(cascade={ALL},fetch=FetchType.EAGER,mappedBy="user")
	protected List<Friendship> requests;
	
	@JsonIgnore
	public List<Friendship> getRequests() {
		return requests;
	}

	public void setRequests(List<Friendship> requests) {
		this.requests = requests;
	}

	@JsonIgnore
	public List<Friendship> getFriendships() {
		return friendships;
	}

	public void setFriendships(List<Friendship> friendships) {
		this.friendships = friendships;
	}

	@Column(name="email",nullable = false)
	protected String email;
	
	@Column(nullable = false)
	protected String password;

	@Column(nullable = false)
	protected String name;

	@Column(nullable = false)
	protected String surname;

	@Column(nullable = true)
	protected String city;

	@Column(nullable=false)
	protected boolean activated;
	
	@Column(nullable=false)
	protected boolean firstlogin;
	
	@Column(nullable=false)
	protected UserRole role;
	
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}


	public User(Long id, List<Friendship> friendships, String email, String password,
			String name, String surname, String city, boolean activated, boolean firstlogin, UserRole role,
			String number) {
		super();
		this.id = id;
		this.friendships = friendships;
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.activated = activated;
		this.firstlogin = firstlogin;
		this.role = role;
		this.number = number;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public boolean isFirstlogin() {
		return firstlogin;
	}

	public void setFirstlogin(boolean firstlogin) {
		this.firstlogin = firstlogin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}

	



	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", name=" + name + ", surname=" + surname + ", city="
				+ city + ", number=" + number + "]";
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(nullable = true)
	protected String number;

	
	
	public User(){
		
	}
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
