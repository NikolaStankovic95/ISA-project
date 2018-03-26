package com.pomoravskivrbaci.cinemareservations.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Reservation {

	
	public Seat getSeats() {
		return seats;
	}
	public void setSeats(Seat seats) {
		this.seats = seats;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@OneToOne
	protected Projection projection;

	@OneToOne
	protected Hall hall;

	@OneToOne
	protected Seat seats;

	@OneToOne
	protected Period period;

	@OneToOne
	protected Institution institution;
	
	@ManyToOne
	protected User owner;
	
	public Reservation(Long id, Projection projection, Hall hall, Seat seats, Period period, Institution institution,
			User owner) {
		super();
		this.id = id;
		this.projection = projection;
		this.hall = hall;
		this.seats = seats;
		this.period = period;
		this.institution = institution;
		this.owner = owner;
		
	}
	public Reservation() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Projection getProjection() {
		return projection;
	}
	public void setProjection(Projection projection) {
		this.projection = projection;
	}
	public Hall getHall() {
		return hall;
	}
	public void setHall(Hall hall) {
		this.hall = hall;
	}
	public Seat getSeat() {
		return seats;
	}
	public void setSeat(Seat seats) {
		this.seats = seats;
	}
	public Period getPeriod() {
		return period;
	}
	public void setPeriod(Period period) {
		this.period = period;
	}
	public Institution getInstitution() {
		return institution;
	}
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	
	
}