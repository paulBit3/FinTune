package com.yetaidev.dto;

import lombok.Data;

/**
 * data transfer object class for login request
 */

@Data
public class LoginRequest {
	private String username;
	private String email;
	private String password;
}
