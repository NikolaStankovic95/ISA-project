package com.pomoravskivrbaci.cinemareservations.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Institution {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Column(name="name" ,nullable=false)
	private String name;
	
	@Column(name="type" ,nullable=false)
	private InstitutionType type;

	public String getName() {
		return name;
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
