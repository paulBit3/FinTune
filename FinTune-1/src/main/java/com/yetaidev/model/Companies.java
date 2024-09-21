package com.yetaidev.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * This entity represents companies that hold the stocks
 */


@Entity
@Data
@Builder   /* make the class to be instantiable */
@AllArgsConstructor
@NoArgsConstructor
public class Companies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //generating unique IDentifier for company
	private Long cmp_id;
	
	private String name;
	private String symbol;
	private String address;
	
	@Column(unique = true)
	private String email;
	
	//we have a N to N relationship between Company and Stock
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "company", 
		joinColumns = @JoinColumn(name = "cmp_id"),
		inverseJoinColumns = @JoinColumn(name = "symbol"))
	private Set<StockPrices> stockPrices = new HashSet<>();

}
