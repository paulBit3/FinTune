package com.yetaidev.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
//import jakarta.persistence.Table;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/*
 * entity class that reprrsents a user using the app
 */

@Entity
@Data
@Builder   /* make the class to be instantiable */
@AllArgsConstructor
//@NoArgsConstructor //this is if we don't want constructor
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //generating unique IDentifier for user
	@Column(nullable = false)
	private Long id;
	
	@Column(unique = true, nullable = false)
	@Size(max= 100)
	private String username;
	
	
	@Column(unique = true, nullable = false)
	@Size(max= 255)
	private String email;
	
	@Column(nullable = false)
	@Size(max= 100)
	private String password;
	
	@Column(nullable = false)
	@Size(max= 150)
	private String full_name;
		
	@Column(nullable = false)
	@Size(max= 20)
	private String phone;
	
	@Size(max= 25)
	private String country;
	
	@Size(max= 25)
	private String state;
	
	@Size(max= 25)
	private String city;
	
	@Size(max= 25)
	private String zipcode;
	
	private BigInteger stock_purchase;
	private Double stock_sold;
	private Double profit;
	
	 @CreationTimestamp
	 @Column(updatable = false, name = "created_at")
	 private Date createdAt;

	  @UpdateTimestamp
	  @Column(name = "updated_at")
	  private Date updatedAt;
	
	
	//we have many to many relationship between User and Stock
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_stock_account", 
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "symbol"))
	private Set<StockPrices> stockPrices =  new HashSet<>();
	
	
	//we have many to many relationship between User and Account
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_stock_account", 
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "accnt_id"))
	private Set<Account> account = new HashSet<>();

	//we set relationship between user table and role table
	 @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "user_roles",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	
	//constructors
	public Users(String username, String email, String password, String full_name, String phone) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.full_name = full_name;
		this.phone = phone;
	}
	
	
	public Users() {}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


//	public void setRoles(String user) {
//		this.setRoles(roles);	
//	}
	
//	public String setRoles(String user) {
//		this.setRoles(roles);
//		return user;	
//	}
	
	
	
}
