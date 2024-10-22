package com.jagan.JobReviews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jagan.JobReviews.Dto.JobsDto;
import com.jagan.JobReviews.service.JobService;

@RestController
@RequestMapping("/{compId}/jobs")
public class JobController {
	@Autowired
	private JobService service ;
	
	@GetMapping("/all")
	public ResponseEntity<List<JobsDto>> findAllJobs(@PathVariable Long compId){
		return service.findAllJobs(compId);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addJob(@RequestBody JobsDto dto,@PathVariable Long compId){
		return service.addJob(dto,compId);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JobsDto> findJob(@PathVariable long id,@PathVariable Long compId){
		return service.findJob(id,compId);
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@PathVariable long id,@RequestBody JobsDto dto,@PathVariable Long compId){
		return service.updateJob(id,dto,compId);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable long id,@PathVariable Long compId){
		return service.deleteJob(id,compId);
		
	}
	
	

}
