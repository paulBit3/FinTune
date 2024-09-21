/**
 * 
 */
package com.yetaidev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yetaidev.dto.CompanyResponse;
import com.yetaidev.model.Companies;
import com.yetaidev.repository.CompaniesDao;

import lombok.extern.slf4j.Slf4j;

/**
 * Company controller
 */

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/")
public class CompaniesController {
	// /company
	
	@Autowired
	private CompaniesDao cmpyDao;
		
	/*
	 * POST ENDPOINT
	 */
	@PostMapping("/company")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	Companies newCompany(@RequestBody Companies newCompany) {
		//log.info(null)
		return cmpyDao.save(newCompany);
	}
		
	
	/*
	 * GET ENDPOINT
	 */
		
	@GetMapping("/companies")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	List<CompanyResponse> getALlCompany(){
		log.info("Get all companies");
		return cmpyDao.findAll()
				.stream().map(this::mapToCompanyResponse)
				.toList();
	}
		
	//mapping method to map comapny
	private CompanyResponse mapToCompanyResponse(Companies cmpy) {
		return CompanyResponse.builder()
				.cmp_id(cmpy.getCmp_id())
				.name(cmpy.getName())
				.symbol(cmpy.getSymbol())
				.address(cmpy.getAddress())
				.email(cmpy.getEmail())
				.build();
	}
		
	//get company by id
	@GetMapping("/company/{cmp_id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Companies getCompyById(@PathVariable Long cmp_id) {
		return cmpyDao.findById(cmp_id).orElseThrow(RuntimeException::new);
	}
		
		//get company by stock symbol
//		@GetMapping("/company/{symbol}")
//		public CompanyResponse getCompanyByStockSymbol(@PathVariable String symbol) {
//			return cmpyDao.findBySymbol(symbol);
//			//this method will allow us to find company by stock symbol
		//CompanyResponse findBySymbol(String symbol);
//		}
		

	/*
	 * PUT ENDPOINT
	 */
	@PutMapping("/company/{cmp_id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Companies updateCompany(@PathVariable Long cmp_id,
			@RequestBody Companies cmpy) {
		cmpy.setCmp_id(cmp_id);
		return newCompany(cmpy);
	}
		

	/*
	 * DELETE ENDPOINT
	 */
		
	//delete company by Id. I use ResponseEntity to easily manipulate Http response
	@DeleteMapping("/company/{cmp_id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity deleteCompany(@PathVariable Long cmp_id) {
		cmpyDao.deleteById(cmp_id);
		log.info("Company was deleted successfully.", HttpStatus.OK);
		return ResponseEntity.ok().build() ;
	}
		
		

}
