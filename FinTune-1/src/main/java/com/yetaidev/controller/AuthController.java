package com.yetaidev.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yetaidev.api.integration.AlphaVantageAPIStockService;
import com.yetaidev.app.exception.UserInfoException;
import com.yetaidev.app.security.AuthTokenProvider;
import com.yetaidev.app.security.WebSecurityConfig;
import com.yetaidev.auth.service.AuthService;
import com.yetaidev.auth.service.AuthServiceImpl;
import com.yetaidev.dto.AuthResponse;
import com.yetaidev.dto.LoginRequest;
import com.yetaidev.dto.RegisterRequest;
import com.yetaidev.dto.UsersResponse;
import com.yetaidev.model.Role;
import com.yetaidev.model.RoleName;
import com.yetaidev.model.Users;
import com.yetaidev.repository.RoleDao;
import com.yetaidev.repository.UsersDao;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * System auyhentication controller
 */

@CrossOrigin
@RestController
@RequestMapping("/")
@Slf4j
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthService authService;
	//private AuthServiceImpl authServiceImpl;
	
//	@Autowired
//	private UsersDao mUserDao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	AuthTokenProvider authTokenProvider;
	
	/*
	 * POST ENDPOINT
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/signup")
	AuthResponse register(@Valid @RequestBody RegisterRequest regReq) {
		//check if username already exist
		if (authService.hasUserWithUsername(regReq.getUsername())) {
			throw new UserInfoException(String.format("Username %s already taken", regReq.getUsername()));
		}
		//check if email already exist
		if (authService.hasUserWithEmail(regReq.getEmail())) {
			throw new UserInfoException(String.format("Username %s already taken", regReq.getEmail()));
		}
		mapToUsersRegisterRequest(regReq);
		
		//call generate token method here
		String token = authAndGetToken(regReq.getEmail(), regReq.getPassword());
		return new AuthResponse(token);
	}
	
	//method to generate token
	private String authAndGetToken(String email, String password) {
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, password));
		return authTokenProvider.generateToken(auth);
	}

	//mapping function for user registration request
	private Users mapToUsersRegisterRequest(@Valid RegisterRequest regReq) {
		Users user = new Users(
				regReq.getUsername(),
				regReq.getEmail(),
				encoder.encode(regReq.getPassword()),
				regReq.getFull_name(),
				regReq.getPhone());

		
		Set<String> strRoles = regReq.getRole();
		Set<Role> roles = new HashSet<>();
		
		//if no role, add a new role
		if (strRoles == null) {
			Role userRole = roleDao.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleDao.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role not found."));
					roles.add(adminRole);
					break;
				case "moderator":
					Role modRole = roleDao.findByName(RoleName.ROLE_MODERATOR)
						.orElseThrow(() -> new RuntimeException("Error: Role not found."));
					roles.add(modRole);
					break;
				default: 
					Role userRole = roleDao.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role not found."));
					roles.add(userRole);
				}
			});
		}
		
		user.setRoles(roles);
		authService.newUser(user);
		//mUserDao.save(user);
		
		return user;
	}


	/*
	 * GET ENDPOINT
	 */
	
	//login with email
	@PostMapping("/login1")
	AuthResponse signin(@Valid @RequestBody LoginRequest logReq) {
		String token = authAndGetToken(logReq.getEmail(), logReq.getPassword());
		log.info(token);
		return new AuthResponse(token);
		
	}
	
	
	//login with username
//	@GetMapping("/login2")
//	AuthResponse signin2(@Valid @RequestBody LoginRequest logReq) {
//		String token = authAndGetToken(logReq.getUsername(), logReq.getPassword());
//		return new AuthResponse(token);
//	}
	
	
	//logout
	@GetMapping("/logout")
	public String logout(BindingResult bindingRes, HttpSession session, Model model) {
		UsersResponse uRes = (UsersResponse) session.getAttribute("username");
		session.invalidate();
		
		return "/";
	}
	
	
}
