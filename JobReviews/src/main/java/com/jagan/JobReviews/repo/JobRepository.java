package com.jagan.JobReviews.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jagan.JobReviews.entity.Jobs;

public interface JobRepository extends JpaRepository<Jobs,Long>{

	List<Jobs> findByCompanyId(Long compId);

}
