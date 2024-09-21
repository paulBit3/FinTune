package com.yetaidev.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yetaidev.model.Users;

/**
 * User Authentication service class
 */
@Service
public interface AuthService {
	Users newUser(Users newUser);
	
	Optional<Users> getUserByEmail(String email);
	
	Optional<Users> getUserByUsername(String username);
	
	boolean hasUserWithUsername(String username);

    boolean hasUserWithEmail(String email);
}
