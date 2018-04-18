package com.pomoravskivrbaci.cinemareservations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Period implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Column(name="date", nullable=false)
	protected Date date;

	@Column(name="date_end", nullable=false)
	protected Date dateEnd;

	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name="period_hall", joinColumns=@JoinColumn(name="period_id"),
	inverseJoinColumns=@JoinColumn(name="hall_id"))
	protected List<Hall> halls = new ArrayList<>();

	@JsonIgnore
	@ManyToOne()
	protected Projection projection;
	
		
	public Projection getProjection() {
		return projection;
	}


	public void setProjection(Projection projection) {
		this.projection = projection;
	}


	public Period (){}

	
	public Period(List<Hall> halls, Date date, Date dateEnd) {
		super();
		this.halls = halls;
		this.date = date;
		this.dateEnd = dateEnd;
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

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public void addHall(Hall hall) {
		halls.add(hall);
	}
}
