package com.jagan.JobReviews.Dto;

import java.util.List;




public class CompanyDto {
	private Long id;
	private String name;
	private String description;
   private List<JobsDto> jobs;
   private List<ReviewDto> reviews;
public List<ReviewDto> getReviews() {
	return reviews;
}
public void setReviews(List<ReviewDto> reviews) {
	this.reviews = reviews;
}
public CompanyDto() {
	super();
	// TODO Auto-generated constructor stub
}
public CompanyDto(Long id, String name, String description) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public List<JobsDto> getJobs() {
	return jobs;
}
public void setJobs(List<JobsDto> jobs) {
	this.jobs = jobs;
}
   
}
