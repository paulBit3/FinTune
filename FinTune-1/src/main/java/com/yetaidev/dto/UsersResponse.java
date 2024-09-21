package com.yetaidev.dto;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.yetaidev.model.Account;
import com.yetaidev.model.Role;
import com.yetaidev.model.StockPrices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;





/**
 * This class act like a Data Transfer Object DTO.
 * It is for separate the Users entity from the Data Transfer Object.
 * This is good practices to not expose our model entity.
 */



@Data
@Builder   /* make the class to be instantiable */
@AllArgsConstructor
//@NoArgsConstructor
public class UsersResponse {
	
	private Long id;
	private String username;
	private String email;
	private String password;
	private String full_name;
	private String phone;
	private String country;
	private String state;
	private String city;
	private String zipcode;
	private BigInteger stock_purchase;
	private Double stock_sold;
	private Double profit;
	private Date createdAt;
	private Date updatedAt;
	
	
	private Set<StockPrices> stockPrices =  new HashSet<>();
	private Set<Account> account = new HashSet<>();
	private Set<Role> roles = new HashSet<>();
	
	//constructors
	public UsersResponse(String username, String email, String password, String full_name, String phone) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.full_name = full_name;
		this.phone = phone;
	}
	
	public UsersResponse() {}

	  
    
	
}
