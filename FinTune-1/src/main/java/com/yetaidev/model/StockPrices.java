package com.yetaidev.model;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * stock entity class that represent the stock data for particular company
 */


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder   /* make the class to be instantiable */
public class StockPrices {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stock_id;
	
	private String symbol;
	private LocalDate date;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Long volume;

	
	//we have a N to 1 relationship between Vendor and Stock
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vendor_id")
	private DataVendor vendor;
	
	
	//we have many(N) to many(N) relationship between Stock and User
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "stockPrices",cascade = CascadeType.PERSIST)
	private Set<Users> users = new HashSet<>();
	
	
	//we have a N to N relationship between Stock and company
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "stockPrices",cascade = CascadeType.PERSIST)
	private  Set<Companies> companies = new HashSet<>();

	
}
