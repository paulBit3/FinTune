package com.yetaidev.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yetaidev.model.Companies;
import com.yetaidev.model.StockPrices;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * This class act like a Data Transfer Object DTO.
 * It is for separate the Company entity from the Data Transfer Object.
 * This is good practices to not expose our model entity.
 */



@Data
@Builder   /* make the class to be instantiable */
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse {
	
	private Long cmp_id;
	private String name;
	private String symbol;
	private String address;
	private String email;
	
	private Set<StockPrices> stockPrices = new HashSet<>();
	
	public CompanyResponse(Companies company) {
		for (StockPrices sp: company.getStockPrices()) {
			stockPrices.add(sp);
		}
	}
}
