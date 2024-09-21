/**
 * 
 */
package com.yetaidev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yetaidev.model.Users;

/**
 * User member data access object class
 */

@Repository
public interface UsersDao extends JpaRepository<Users, Long> {
	//find by email
	Optional<Users> findByEmail(String email);
			
	//get user by email
	Optional<Users> getUserByEmail(String email);
	
	//get userr by username
	Optional<Users> getUserByUsername(String username);

		
	//
	Optional<Users> findByIdIn(List<Long> userIds);
			
	//find by username
	Optional<Users> findByUsername(String username);

	Users save(Users newUser);
	
	//boolean value
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
