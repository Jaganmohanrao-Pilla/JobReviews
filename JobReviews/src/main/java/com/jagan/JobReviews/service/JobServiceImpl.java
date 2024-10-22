package com.jagan.JobReviews.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jagan.JobReviews.Dto.CompanyDto;
import com.jagan.JobReviews.Dto.JobsDto;
import com.jagan.JobReviews.entity.Company;
import com.jagan.JobReviews.entity.Jobs;
import com.jagan.JobReviews.repo.CompanyRepository;
import com.jagan.JobReviews.repo.JobRepository;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobRepository repo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CompanyRepository comprepo;

	@Override
	public ResponseEntity<List<JobsDto>> findAllJobs(Long compId) {
		List<Jobs> jobslist = repo.findByCompanyId(compId);
		/*
		 * Optional<Company> findById = comprepo.findById(compId); CompanyDto company =
		 * mapper.map(comprepo.findById(compId).orElse(null),CompanyDto.class);
		 */
		if(!jobslist.isEmpty()) {
			List<JobsDto> jobsdto=new ArrayList<>();
			for(Jobs job:jobslist) {
				/*
				 * JobsDto dto=new JobsDto(); dto.setId(job.getId());
				 * dto.setDescription(job.getDescription()); dto.setLocation(job.getLocation());
				 * dto.setMaxSalary(job.getMaxSalary()); dto.setMinSalary(job.getMinSalary());
				 * dto.setTitle(job.getTitle());
				 */
				JobsDto dto = mapper.map(job,JobsDto.class);
				jobsdto.add(dto);
			}
			return new ResponseEntity<List<JobsDto>>(jobsdto,HttpStatus.OK);
		}
		return new ResponseEntity<List<JobsDto>>(HttpStatus.NO_CONTENT);
		
	}

	@Override
	public ResponseEntity<String> addJob(JobsDto dto,Long compId) {
		/*
		 * Jobs job=new Jobs(); job.setTitle(dto.getTitle());
		 * job.setDescription(dto.getDescription()); job.setLocation(dto.getLocation());
		 * job.setMinSalary(dto.getMinSalary()); job.setMaxSalary(dto.getMaxSalary());
		 */
		CompanyDto companyDto = mapper.map(comprepo.findById(compId).orElse(null),CompanyDto.class);
		if(companyDto!=null) {
			Company company = mapper.map(companyDto,Company.class);
			Jobs job = mapper.map(dto, Jobs.class);
            job.setCompany(company);
            repo.save(job);
            return new ResponseEntity<String>("Job added successfully",HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<String>("Some error occured,invlid company id",HttpStatus.NOT_FOUND);	
		}	
	}

	@Override
	public ResponseEntity<JobsDto> findJob(Long id,Long compid) {
		CompanyDto companyDto = mapper.map(comprepo.findById(compid).orElse(null),CompanyDto.class);
		if(companyDto!=null) {
            Jobs job = repo.findById(id).orElse(null);
            if(job!=null) {
            	//JobsDto dto=new JobsDto(job.getId(),job.getTitle(),job.getDescription(),job.getDescription(),job.getMaxSalary(),job.getMinSalary());
				
				  JobsDto dto = mapper.map(job,JobsDto.class);
				  dto.setCompanydto(companyDto);
				 
            	return new ResponseEntity<JobsDto>(dto,HttpStatus.OK);
            }
            else {
            	return new ResponseEntity<JobsDto>(HttpStatus.NO_CONTENT);
            }
		}
		return new ResponseEntity<JobsDto>(HttpStatus.NOT_FOUND);
		
	}

	@Override
	public ResponseEntity<String> updateJob(Long id, JobsDto dto,Long compId) {
		CompanyDto companyDto = mapper.map(comprepo.findById(compId).orElse(null),CompanyDto.class);
		if(companyDto!=null) {
			Jobs job = repo.findById(id).orElse(null);
	        if(job!=null) {
	        	    job.setTitle(dto.getTitle());
	        	    job.setDescription(dto.getDescription());
	        	    job.setLocation(dto.getLocation());
	        	    job.setMaxSalary(dto.getMaxSalary());
	        	    job.setMinSalary(dto.getMinSalary());
	        	    repo.save(job);
	                return new ResponseEntity<String>("Job updated",HttpStatus.OK);
		}
	        else {
	        	 return new ResponseEntity<String>(" Job Not found",HttpStatus.NOT_FOUND);
	        }
		    
        }
       return new ResponseEntity<String>(" company Not found",HttpStatus.NOT_FOUND);
	
	}

	@Override
	public ResponseEntity<String> deleteJob(Long id,Long compId) {
		CompanyDto companyDto = mapper.map(comprepo.findById(compId).orElse(null),CompanyDto.class);
		if(companyDto!=null) {
			Jobs job = repo.findById(id).orElse(null);
			if(job!=null) {
			Company company = job.getCompany();
			List<Jobs> jobs = company.getJobs();
			jobs.remove(job);
			company.setJobs(jobs);
			comprepo.save(company);
		    repo.deleteById(id);
				return new ResponseEntity<String>("Job Deleted",HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>(" Job Not found",HttpStatus.NOT_FOUND);
			}
		}
		
		else {
			return new ResponseEntity<String>(" Company Not found",HttpStatus.NOT_FOUND);
		}
			}

}
