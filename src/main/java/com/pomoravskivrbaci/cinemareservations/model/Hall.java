package com.pomoravskivrbaci.cinemareservations.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

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

	@OneToMany(mappedBy="hall", cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
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

	public void setHallSegments(List<HallSegment> hallSegments) {
		this.hallSegments = hallSegments;
	}

	public HallSegment getHallSegmentByType(HallSegment.Type type) {
		return hallSegments.stream()
				.filter(e -> e.getType().equals(type))
				.findFirst()
				.orElse(null);
	}
}
