package com.jagan.JobReviews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long rating;
	
	private String description;
	
   @ManyToOne
   @JsonIgnore
   private Company company;

public Review() {
	super();
	// TODO Auto-generated constructor stub
}

public Review(Long id, Long rating, String description, Company company) {
	super();
	this.id = id;
	this.rating = rating;
	this.description = description;
	this.company = company;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Long getRating() {
	return rating;
}

public void setRating(Long rating) {
	this.rating = rating;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Company getCompany() {
	return company;
}

public void setCompany(Company company) {
	this.company = company;
}
   
	
	

}
