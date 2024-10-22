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

import com.jagan.JobReviews.Dto.ReviewDto;
import com.jagan.JobReviews.service.ReviewService;

@RestController
@RequestMapping("/company/{compId}")
public class ReviewController {
	@Autowired
	private ReviewService service;
	
	
	
	@GetMapping("/reviews/all")
	public ResponseEntity<List<ReviewDto>> getAllReviews(@PathVariable Long compId){
		return service.getAllReviews(compId);
	}
	@GetMapping("/reviews/{id}")
	public ResponseEntity<ReviewDto> getRewiewById(@PathVariable Long compId,@PathVariable Long id){
		return service.getReviewById(compId,id);
	}
	
	//update reviews
	
	@PutMapping("/reviews/{id}")
	public ResponseEntity<String> updateReview(@PathVariable Long compId,@PathVariable Long id,@RequestBody ReviewDto dto){
		return service.updateReview(compId,id,dto);
	}
	
	//delte reviews
	@DeleteMapping("/reviews/{id}")
	public ResponseEntity<String> deleteReview(@PathVariable Long compId,@PathVariable Long id){
		return service.deleteReview(compId,id);
	}
	
	//create reviews
    @PostMapping("/reviews/create")
	public ResponseEntity<String> createReview(@PathVariable Long compId,@RequestBody ReviewDto dto){
		return service.createReview(compId,dto);
	}
	
}
