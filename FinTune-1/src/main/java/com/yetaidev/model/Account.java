package com.yetaidev.model;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * This entity represents the registered member users account that manage 
 * the user financial account such as( bank accnt, balance, accnt type)
 */

@Entity
@Data
@Builder   /* make the class to be instantiable */
@AllArgsConstructor
//@NoArgsConstructor
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //generating unique IDentifier for account
	private Long accnt_id;
	
	private String type;
	private String description;
	private Double balance;
	private LocalDate transaction_date;
	
	
	//we have many to many relationship between Stock and User
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "account", cascade = CascadeType.PERSIST)
	private Set<Users> users =  new HashSet<>();
	
	
	//contructor
	public Account(String type, String description, Double balance, LocalDate transaction_date) {
		super();
		this.type = type;
		this.description = description;
		this.balance = balance;
		this.transaction_date = transaction_date;
	}
	
	public Account() {}
	
}
