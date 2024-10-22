package com.jagan.JobReviews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jagan.JobReviews.Dto.JobsDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;


@Entity
public class Jobs {
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String title;
	private String description;
	private String location;
	private Long maxSalary;
	private Long minSalary;
	@ManyToOne
	@JsonIgnore
	private Company company;
	public Jobs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Jobs(Long id, String title, String description, String location, Long maxSalary, Long minSalary) {
		super();
		Id = id;
		this.title = title;
		this.description = description;
		this.location = location;
		this.maxSalary = maxSalary;
		this.minSalary = minSalary;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Long getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(Long maxSalary) {
		this.maxSalary = maxSalary;
	}
	public Long getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(Long minSalary) {
		this.minSalary = minSalary;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public JobsDto jobEntityToDto(Jobs job) {
		JobsDto dto=new JobsDto();
		dto.setId(job.getId());
		dto.setTitle(job.getTitle());
		dto.setDescription(job.getDescription());
		dto.setMaxSalary(job.getMaxSalary());
		dto.setMinSalary(job.getMinSalary());
		dto.setLocation(job.getLocation());
		//dto.setCompanydto(job.getCompany());
		return dto;
		
	}
	

}
