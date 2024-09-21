package com.yetaidev.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.yetaidev.model.DataVendor;
import com.yetaidev.model.StockPrices;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * This class act like a Data Transfer Object DTO.
 * It is for separate the DataVendor entity from the Data Transfer Object.
 * This is good practices to not expose our model entity.
 */



@Data
@Builder   /* make the class to be instantiable */
@AllArgsConstructor
@NoArgsConstructor
public class DataVendorResponse {
	
	private Long vendor_id;
	private String description;
	private String web_url;
	private LocalDate created_date;
	private LocalDate last_updated;
	
	private Set<StockPrices> stockPrices = new HashSet<>();
}
