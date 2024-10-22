package com.jagan.JobReviews.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jagan.JobReviews.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByCompanyId(Long compId);

}
