package com.yetaidev.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.yetaidev.model.Users;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * This class act like a Data Transfer Object DTO.
 * It is for separate the Account entity from the Data Transfer Object.
 * This is good practices to not expose our model entity.
 */



@Data
@Builder   /* make the class to be instantiable */
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
	
	private Long accnt_id;
	private String type;
	private String description;
	private Double balance;
	private LocalDate transaction_date;
	
	private Set<Users> users =  new HashSet<>();
}
