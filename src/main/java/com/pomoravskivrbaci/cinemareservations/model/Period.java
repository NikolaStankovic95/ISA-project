package com.pomoravskivrbaci.cinemareservations.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Period implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Column(name="date",unique=true,nullable=false)
	protected Date date;
	
	@ManyToMany
	@JoinTable(name="period_hall", joinColumns=@JoinColumn(name="period_id"),
	inverseJoinColumns=@JoinColumn(name="hall_id"))
	protected List<Hall> halls;
	
	@ManyToOne
	protected Projection projection;
	
		
	public Projection getProjection() {
		return projection;
	}


	public void setProjection(Projection projection) {
		this.projection = projection;
	}


	public Period (){}

	
	public Period(List<Hall> halls, Date date) {
		super();
		this.halls = halls;
		this.date = date;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Hall> getHalls() {
		return halls;
	}

	public void setHalls(List<Hall> halls) {
		this.halls = halls;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
