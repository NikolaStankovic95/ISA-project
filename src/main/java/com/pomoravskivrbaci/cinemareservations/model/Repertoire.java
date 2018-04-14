package com.pomoravskivrbaci.cinemareservations.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Repertoire implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//DATUM REPERTOARA SE BIRA IZ KALENDARA
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Column(name="name")
	protected String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "repertoire")
	protected List<Institution> institutions = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public List<Institution> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(List<Institution> institutions) {
		this.institutions = institutions;
	}
	@JsonIgnore
	public List<Projection> getProjections() {
		return projections;
	}

	public void setProjections(List<Projection> projections) {
		this.projections = projections;
	}

	@ManyToMany(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name="repertoire_projection", joinColumns=@JoinColumn(name="repertoire_id"),
	inverseJoinColumns=@JoinColumn(name="projection_id"))
	protected List<Projection> projections = new ArrayList<>();
	
	public Repertoire (){}

	public void addInstitution(Institution institution) {
		institutions.add(institution);
	}

	public void addProjection(Projection projection) {
		projections.add(projection);
	}
}
