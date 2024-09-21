package com.yetaidev.dto;

/**
 * data transfer object for user authentication
 */
public class AuthResponse {
	String accessToken;
	
	//constructor
	public AuthResponse(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}
	
}
