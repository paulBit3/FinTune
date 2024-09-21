package com.yetaidev.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.yetaidev.model.Companies;
import com.yetaidev.model.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * This class act like a Data Transfer Object DTO.
 * It is for separate the Stock Prices entity from the Data Transfer Object.
 * This is good practices to not expose our model entity to the world as we're dealing with
 * 3rd party API integration
 */


@Data
@Builder   /* make the class to be instantiable */
@AllArgsConstructor
@NoArgsConstructor
public class StockResponse {
	private Long stock_id;
	private String symbol;
	private LocalDate date;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Long volume;
	
	private Set<Users> users = new HashSet<>();
	private  List<Companies> companies = new ArrayList<>();
}
