package com.jagan.JobReviews.Dto;

public class JobsDto {
		private Long Id;
		private String title;
		private String description;
		private String location;
		private Long maxSalary;
		private Long minSalary;
		private CompanyDto companydto;
		public CompanyDto getCompanydto() {
			return companydto;
		}
		public void setCompanydto(CompanyDto companydto) {
			this.companydto = companydto;
		}
		public JobsDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		public JobsDto(Long id, String title, String description, String location, Long maxSalary, Long minSalary) {
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
		@Override
		public String toString() {
			return "JobsDto [Id=" + Id + ", title=" + title + ", description=" + description + ", location=" + location
					+ ", maxSalary=" + maxSalary + ", minSalary=" + minSalary + "]";
		}
		

	}

