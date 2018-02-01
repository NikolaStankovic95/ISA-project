package com.pomoravskivrbaci.cinemareservations.model;

import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Institution implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Column(name="name" ,nullable=false)
	private String name;
	
	@Column(name="type" ,nullable=false)
	private InstitutionType type;

	@Column(name="address", nullable = false)
	private String address;

	@Column(name="description", nullable = false)
	private String description;

	@Column(name="rating", nullable = false)
	private float rating;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "institution")
	protected List<Hall> halls;
	
	@ManyToOne
	protected Repertoire repertoire;
	
	
	public Repertoire getRepertoire() {
		return repertoire;
	}

	public void setRepertorie(Repertoire repertoire) {
		this.repertoire = repertoire;
	}
	
	@JsonIgnore
	public List<Hall> getHalls() {
		return halls;
	}

	public void setHalls(List<Hall> halls) {
		this.halls = halls;
	}

	
	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Institution(){
		
	}
	public Institution(String name, InstitutionType type) {
		super();
		this.name = name;
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InstitutionType getType() {
		return type;
	}

	public void setType(InstitutionType type) {
		this.type = type;
	}
	
}
