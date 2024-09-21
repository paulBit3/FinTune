/**
 * 
 */
package com.yetaidev.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yetaidev.auth.service.AuthServiceImpl;
import com.yetaidev.dto.UsersResponse;
import com.yetaidev.model.Users;
import com.yetaidev.repository.UsersDao;

/**
 * Usermember controller
 */


@CrossOrigin //this allows our backend to be called by the frontend client @CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/")
public class UsersController {
	@Autowired
	private UsersDao mUserDao;
	
	@Autowired
	private AuthServiceImpl authServiceImpl;
	
	// Logger
	
	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);


	
	
	
	/*
	 * GET ENDPOINT
	 */
	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<UsersResponse> getAllUsers(){
		List<Users> userEntities = mUserDao.findAll();
		
		List<UsersResponse> userResponses = userEntities
				.stream()
				.map(this::mapToUsersResponse)
				.toList() //we used toList..instead of collector to make the code neat list and  to reduce the verbosity of the Collector API. Stream.toList() uses less memory
				//.collect(Collectors.toList())
				;
		return userResponses;	
	}
	
	
	//mapping method to map member user
	private UsersResponse mapToUsersResponse(Users user) {
		return UsersResponse.builder()
				.id(user.getId())
				.username(user.getUsername())
				.email(user.getEmail())
				.full_name(user.getFull_name())
				.phone(user.getPhone())
				.country(user.getCountry())
				.state(user.getState())
				.city(user.getCity())
				.zipcode(user.getZipcode())
				.stock_purchase(user.getStock_purchase())
				.stock_sold(user.getStock_sold())
				.profit(user.getProfit())
				.build();
			
	}
	
	//get member or user by id
	@Transactional
	@GetMapping("/user/{user_id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Users getUserById(@PathVariable Long id) {
			return mUserDao.findById(id).orElseThrow(RuntimeException::new);
	}
	
	
	//find by email
	@GetMapping("/user/{email}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Optional<Users>> getUserByEmail(@PathVariable String email) {
		try {
			Optional<Users> user = mUserDao.findByEmail(email);
			return ResponseEntity.ok(user);
		} catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	//find by username
	@GetMapping("/user/{username}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Optional<Users>> getUserByUsername(@PathVariable String username) {
		try {
			Optional<Users> user = mUserDao.findByUsername(username);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@GetMapping("/user/")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Users findByUsernameOrEmail(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//check if user exit by username
	
	//check if user exit by email
	
	
	/*
	 * PUT ENDPOINT
	 */
	
	//update member or user
	@PutMapping("/user/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Users updateUser(@PathVariable Long id, 
			@RequestBody Users user) {
		user.setId(id);
		return authServiceImpl.newUser(user);
	}
		
	
	/*
	 * DELETE ENDPOINT
	 */
	
	//delete member or user by Id
	@DeleteMapping("/user/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity deleteUser(@PathVariable Long id) {
		mUserDao.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	

	
	
	
}
