package com.jagan.JobReviews.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jagan.JobReviews.Dto.CompanyDto;
import com.jagan.JobReviews.Dto.JobsDto;
import com.jagan.JobReviews.entity.Company;
import com.jagan.JobReviews.entity.Jobs;
import com.jagan.JobReviews.entity.Review;
import com.jagan.JobReviews.repo.CompanyRepository;
import com.jagan.JobReviews.repo.JobRepository;
import com.jagan.JobReviews.repo.ReviewRepository;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyRepository repo;

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private JobRepository jobrepo;
	@Autowired
	private ReviewRepository revrepo;
	

	@Override
	public ResponseEntity<List<CompanyDto>> getAllCompanies() {
		List<Company> companies = repo.findAll();
		if (!companies.isEmpty()) {
			List<CompanyDto> companiesDto = new ArrayList<>();
			for (Company c : companies) {
				// CompanyDto dto=new CompanyDto();
				CompanyDto dto = mapper.map(c, CompanyDto.class);
				// dto.setId(c.getId());
				// dto.setName(c.getName());
				// List<JobsDto> jobsdto=new ArrayList<>();
				// List<Jobs> jobs = c.getJobs();
				/*
				 * if(!jobs.isEmpty()) { for(Jobs job:jobs) { JobsDto jobdto=new
				 * JobsDto(job.getId(),job.getTitle(),job.getDescription(),job.getLocation(),job
				 * .getMaxSalary(),job.getMinSalary()); jobsdto.add(jobdto); } }
				 * dto.setJobs(jobsdto); dto.setDescription(c.getDescription());
				 */
				companiesDto.add(dto);
			}
			return new ResponseEntity<List<CompanyDto>>(companiesDto, HttpStatus.OK);

		} else {
			return new ResponseEntity<List<CompanyDto>>(HttpStatus.NO_CONTENT);

		}

	}

	@Override
	public ResponseEntity<String> addCompany(CompanyDto dto) {
		//Company company = new Company();
		Company company = mapper.map(dto,Company.class);
		/*
		 * company.setName(dto.getName()); company.setDescription(dto.getDescription());
		 * //List<JobsDto> jobsdto = dto.getJobs(); List<Jobs> jobs=new ArrayList<>();
		 * 
		 * if(!jobsdto.isEmpty()) { for(JobsDto jobdto:jobsdto) { Optional<Jobs> findjob
		 * = jobrepo.findById(jobdto.getId()); Jobs job = findjob.orElse(null);
		 * jobs.add(job); }
		 * 
		 * }
		 * 
		 * company.setJobs(jobs);
		 */
		try {
			repo.save(company);
			return new ResponseEntity<String>("Company Added", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed to add company", HttpStatus.BAD_REQUEST);

		}

	}

	@Override
	public ResponseEntity<CompanyDto> getCompanybyId(Long id) {
		Company company = repo.findById(id).orElse(null);
				
		/*
		 * if (company != null) { CompanyDto dto = new CompanyDto();
		 * dto.setId(company.getId()); dto.setName(company.getName());
		 * dto.setDescription(company.getDescription()); List<JobsDto> jobsdto = new
		 * ArrayList<>(); List<Jobs> jobs = company.getJobs(); if (!jobs.isEmpty()) {
		 * for (Jobs job : jobs) { JobsDto jobdto = new JobsDto(job.getId(),
		 * job.getTitle(), job.getDescription(), job.getLocation(), job.getMaxSalary(),
		 * job.getMinSalary()); jobsdto.add(jobdto); } dto.setJobs(jobsdto); }
		 * 
		 * return new ResponseEntity<CompanyDto>(dto, HttpStatus.OK); } else { return
		 * new ResponseEntity<CompanyDto>(HttpStatus.NOT_FOUND);
		 * 
		 * }
		 */	
		if(company!=null) {
			CompanyDto dto = mapper.map(company, CompanyDto.class);
			return new ResponseEntity<CompanyDto>(dto, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<CompanyDto>(HttpStatus.NOT_FOUND);
		}
		}

	@Override
	public ResponseEntity<String> updateCompany(Long id, CompanyDto dto) {
		Company company = repo.findById(id).orElse(null);
		if (company != null) {
			company.setDescription(dto.getDescription());
			company.setName(dto.getName());
			//company.setJobs(mapper.map(dto.getJobs()));
		    // company.setReviews(dto.getReviews());
			repo.save(company);
			return new ResponseEntity<String>("Company updated", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<String> deleteCompany(Long id) {
		Company company = repo.findById(id).orElse(null);
		if (company != null) {
			/*
			 * List<Jobs> jobs = company.getJobs(); List<Review> reviews =
			 * company.getReviews(); if(!jobs.isEmpty()) { for(Jobs job:jobs) {
			 * jobService.deleteJob(job.getId(), id); } } if(!reviews.isEmpty()) {
			 * for(Review r:reviews) { reviewService.deleteReview(id,r.getId()); } }
			 */
			List<Jobs> jobs= jobrepo.findByCompanyId(id);
			if(!jobs.isEmpty()) {
				for(Jobs j:jobs) {
					jobrepo.deleteById(j.getId());
				}
			}
			List<Review> reviews= revrepo.findByCompanyId(id);
			if(!reviews.isEmpty()) {
				for(Review r:reviews) {
					revrepo.deleteById(r.getId());
				}
			}
			repo.deleteById(id);
			return new ResponseEntity<String>("Company deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Company Not Found", HttpStatus.NOT_FOUND);
		}
	}

}
