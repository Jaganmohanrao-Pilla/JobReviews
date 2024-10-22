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

import com.jagan.JobReviews.Dto.CompanyDto;
import com.jagan.JobReviews.service.CompanyService;

@RestController
@RequestMapping("/Company")
public class CompanyController {
	@Autowired
	private CompanyService service;
	
	@GetMapping("/all")
	public ResponseEntity<List<CompanyDto>> getAllCompanies(){
		 return service.getAllCompanies();
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addCompany(@RequestBody CompanyDto dto){
		return service.addCompany(dto);
	}
	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id){
		return service.getCompanybyId(id);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody CompanyDto dto){
		return service.updateCompany(id,dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id){
		
		return service.deleteCompany(id);
	}

}
