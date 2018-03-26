package com.pomoravskivrbaci.cinemareservations.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Hall implements Serializable {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Column(name="name",nullable=false)
	protected String name;
	
	@OneToMany(mappedBy="hall")
	protected List<Seat> seats;
	
	@ManyToOne
	@JoinColumn(name="institution")
	protected Institution institution;
		
	public Hall() {
		super();
	}
	

	@ManyToMany(mappedBy="halls")
	protected List<Period> periods;
	
	@ManyToMany(mappedBy="halls")
	protected List<Projection> projections;

	@OneToMany(mappedBy="hall")
	protected List<HallSegment> hallSegments;
	
	
	public Hall(String name, Institution institution) {
		super();
		this.name = name;
		this.institution = institution;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public List<HallSegment> getHallSegments() {
		return hallSegments;
	}
}
