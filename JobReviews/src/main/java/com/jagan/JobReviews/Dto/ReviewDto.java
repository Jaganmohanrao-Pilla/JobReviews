package com.jagan.JobReviews.Dto;

public class ReviewDto {
     private Long id;
	
	private Long rating;
	
	private String description;
	
   private CompanyDto companydto;

public ReviewDto() {
	super();
	// TODO Auto-generated constructor stub
}

public ReviewDto(Long id, Long rating, String description, CompanyDto companydto) {
	super();
	this.id = id;
	this.rating = rating;
	this.description = description;
	this.companydto = companydto;
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

public CompanyDto getCompanydto() {
	return companydto;
}

public void setCompanydto(CompanyDto companydto) {
	this.companydto = companydto;
}
   

}
