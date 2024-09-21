package com.yetaidev.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


/*
 * This entity represents the vendor, the party responsible for getting 
 * the stock data to our application. In our case, we chose AlphaVantage as the vendor.
 */


@Entity
@Data
@Builder   /* make the class to be instantiable */
@AllArgsConstructor
@NoArgsConstructor
public class DataVendor {
	@Id //Tells JPA that this is the primary key.
	@GeneratedValue(strategy = GenerationType.IDENTITY) //generating unique IDentifier for vendor
	private Long vendor_id;
	
	private String description;
	private String web_url;
	private LocalDate created_date;
	private LocalDate last_updated;
	
	//we have a 1 to N relationship between Stock and Vendor
	@OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<StockPrices> stockPrices = new HashSet<>();

}
