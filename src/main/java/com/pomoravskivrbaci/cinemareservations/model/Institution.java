package com.pomoravskivrbaci.cinemareservations.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "institution")
	protected List<Hall> halls;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	protected Repertoire repertoire;

	@JsonIgnore
	@OneToMany(mappedBy = "institution")
	protected List<InstitutionRating> ratings;
	
	@ManyToMany
	protected List<User> admins;
	public List<User> getAdmins() {
		return admins;
	}

	public void setAdmins(List<User> admins) {
		this.admins = admins;
	}

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

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Hall getHallById(Long id) {
		return halls.stream()
				.filter(hall -> hall.id == id)
				.findFirst()
				.orElse(null);
	}

	public List<InstitutionRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<InstitutionRating> ratings) {
		this.ratings = ratings;
	}

	@JsonIgnore
	public Double getAverageRating() {
		return ratings.stream()
				.mapToDouble(rating -> rating.getRating())
				.average()
				.orElse(Double.NaN);
	}

	public void addRating(InstitutionRating institutionRating) {
		ratings.add(institutionRating);
	}

}
