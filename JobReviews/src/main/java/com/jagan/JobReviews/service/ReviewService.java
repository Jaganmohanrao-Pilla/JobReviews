package com.jagan.JobReviews.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jagan.JobReviews.Dto.ReviewDto;

public interface ReviewService {

	ResponseEntity<List<ReviewDto>> getAllReviews(Long compId);

	ResponseEntity<ReviewDto> getReviewById(Long compId, Long id);

	ResponseEntity<String> updateReview(Long compId, Long id, ReviewDto dto);

	ResponseEntity<String> deleteReview(Long compId, Long id);

	ResponseEntity<String> createReview(Long compId, ReviewDto dto);

}
