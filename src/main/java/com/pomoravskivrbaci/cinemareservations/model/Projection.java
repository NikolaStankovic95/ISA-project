package com.pomoravskivrbaci.cinemareservations.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Projection implements Serializable{

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

	public void setRepertoires(List<Repertoire> repertoires) {
		this.repertoires = repertoires;
	}

	@ManyToMany(mappedBy="projections")
	protected List<Repertoire> repertoires;
	
	@OneToMany(mappedBy="projection")
	protected List<Period> periods;
	
	@ManyToMany
	@JoinTable(name="projection_hall", joinColumns=@JoinColumn(name="projection_id"),
			inverseJoinColumns=@JoinColumn(name="hall_id"))
	protected List<Hall> halls;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Projection(){
		
	}

	
	@JsonIgnore
	public List<Repertoire> getRepertoires() {
		return repertoires;
	}

	
	
}
