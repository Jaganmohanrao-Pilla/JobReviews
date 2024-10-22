package com.jagan.JobReviews.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jagan.JobReviews.Dto.CompanyDto;

public interface CompanyService {

	ResponseEntity<List<CompanyDto>> getAllCompanies();

	ResponseEntity<String> addCompany(CompanyDto dto);

	ResponseEntity<CompanyDto> getCompanybyId(Long id);

	ResponseEntity<String> updateCompany(Long id, CompanyDto dto);

	ResponseEntity<String> deleteCompany(Long id);

}
