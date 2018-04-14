package com.pomoravskivrbaci.cinemareservations.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	@Column(name="description")
	protected String description;

	@Column(name="price")
	protected Double price;

	@Column(name="duration")
	protected Integer duration;
	
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
	protected List<Repertoire> repertoires = new ArrayList<>();

	@JsonIgnoreProperties("projection")
	@OneToMany(mappedBy="projection")
	protected List<Period> periods;


	@JsonIgnore
	@OneToMany(mappedBy = "projection")
	protected List<ProjectionRating> ratings;

	public List<Period> getPeriods() {
		return periods;
	}

	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}


	public List<Hall> getHalls() {
		return halls;
	}

	public void setHalls(List<Hall> halls) {
		this.halls = halls;
	}

	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinTable(name="projection_hall", joinColumns=@JoinColumn(name="projection_id"),
			inverseJoinColumns=@JoinColumn(name="hall_id"))
	@JsonIgnoreProperties("projections")
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

	public void addRepertoire(Repertoire repertoire) {
		repertoires.add(repertoire);
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public List<ProjectionRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<ProjectionRating> ratings) {
		this.ratings = ratings;
	}

	public Double takeAverageRating() {
		return ratings.stream()
				.mapToDouble(rating -> rating.getRating())
				.average()
				.orElse(Double.NaN);
	}

	public void addRating(ProjectionRating rating) {
		ratings.add(rating);
	}
}
