package com.jagan.JobReviews.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jagan.JobReviews.Dto.CompanyDto;
import com.jagan.JobReviews.Dto.ReviewDto;
import com.jagan.JobReviews.entity.Company;
import com.jagan.JobReviews.entity.Review;
import com.jagan.JobReviews.repo.CompanyRepository;
import com.jagan.JobReviews.repo.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewRepository repo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CompanyRepository comrepo;

	@Override
	public ResponseEntity<List<ReviewDto>> getAllReviews(Long compId) {
		List<Review> reviews=repo.findByCompanyId(compId);
		List<ReviewDto> reviewdtos=new ArrayList<>();
		if(!reviews.isEmpty()) {
			for(Review r:reviews) {
				/*
				 * ReviewDto dto=new ReviewDto(); dto.setId(r.getId());
				 * dto.setDescription(r.getDescription()); dto.setRating(r.getRating()); Company
				 * company = r.getCompany(); CompanyDto cdto=new
				 * CompanyDto(company.getId(),company.getName(),company.getDescription());
				 * dto.setCompanydto(cdto);
				 */
				ReviewDto dto = mapper.map(r,ReviewDto.class);
				CompanyDto  company = mapper.map(comrepo.findById(compId).orElse(null),CompanyDto.class);
				dto.setCompanydto(company);
				reviewdtos.add(dto);	
			}
			return new ResponseEntity<List<ReviewDto>>(reviewdtos,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<ReviewDto>>(HttpStatus.NO_CONTENT);
		}
		 
	}

	@Override
	public ResponseEntity<ReviewDto> getReviewById(Long compId, Long id) {
		List<Review> reviews=repo.findByCompanyId(compId);
		Review review = reviews.stream().filter(r->r.getId().equals(id)).findFirst().orElse(null);
		if(review!=null) {
			ReviewDto dto = mapper.map(review, ReviewDto.class);
			CompanyDto  company = mapper.map(comrepo.findById(compId).orElse(null),CompanyDto.class);
			dto.setCompanydto(company);
			return new ResponseEntity<ReviewDto>(dto,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<ReviewDto>(HttpStatus.NOT_FOUND);

		}
	}

	@Override
	public ResponseEntity<String> updateReview(Long compId, Long id, ReviewDto dto) {
		List<Review> reviews=repo.findByCompanyId(compId);
		Review review = reviews.stream().filter(r->r.getId().equals(id)).findFirst().orElse(null);
		if(review!=null) {
			review.setRating(dto.getRating());
			review.setDescription(dto.getDescription());
			repo.save(review);
			return new ResponseEntity<String>("Review updated",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Review Not Found",HttpStatus.NOT_FOUND);

		}
			}

	@Override
	public ResponseEntity<String> deleteReview(Long compId, Long id) {
		if(comrepo.existsById(compId) && repo.existsById(id)){
			Review review = repo.findById(id).orElse(null);
			if(review!=null) {
				Company company = review.getCompany();
				List<Review> reviews = company.getReviews();
				reviews.remove(review);
				company.setReviews(reviews);
				comrepo.save(company);
				repo.deleteById(id);
				return new ResponseEntity<String>("Review Deleted",HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("Not found",HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<String> createReview(Long compId, ReviewDto dto) {
		Company company = comrepo.findById(compId).orElse(null);
		if(company!=null) {
			Review review = mapper.map(dto, Review.class);
			review.setCompany(company);
			repo.save(review);
			return new ResponseEntity<String>("Review created",HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Company not found",HttpStatus.NOT_FOUND);
	}

}
