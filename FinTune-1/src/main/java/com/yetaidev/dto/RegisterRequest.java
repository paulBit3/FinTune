package com.yetaidev.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * data transfer object class for registration request
 */

@Data
public class RegisterRequest {
	
	@NotBlank
	private String username;
	
	@Email
	private String email;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String full_name;
	
	@NotBlank
	private String phone;
	


	
	private Set<String> role;
	
	public Set<String> getRole() {
		return this.role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}
}
