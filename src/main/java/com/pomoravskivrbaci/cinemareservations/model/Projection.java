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

	@Column(name="actors")
	protected String actors;

	@Column(name="genre")
	protected String genre;

	@Column(name="director_name")
	protected String directorName;

	@Column(name="image_link")
	protected String imageLink;

	@Column(name="rating")
	protected Double rating;

	@Column(name="description")
	protected String description;

	@Column(name="price")
	protected Double price;
	
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

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
