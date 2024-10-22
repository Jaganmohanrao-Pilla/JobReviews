package com.jagan.JobReviews.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jagan.JobReviews.Dto.JobsDto;

public interface JobService {

	ResponseEntity<List<JobsDto>> findAllJobs(Long compId);

	ResponseEntity<String> addJob(JobsDto dto, Long compId);

	ResponseEntity<JobsDto> findJob(Long id, Long compId);

	ResponseEntity<String> updateJob(Long id, JobsDto dto, Long compId);

	ResponseEntity<String> deleteJob(Long id, Long compId);

}
