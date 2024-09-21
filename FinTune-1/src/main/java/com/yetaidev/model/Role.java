package com.yetaidev.model;

import java.util.List;

import org.hibernate.annotations.NaturalId;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
//import jakarta.persistence.Table;
import org.springframework.data.relational.core.mapping.Table;
/*
 * this role class that defune user Roles
 */
@Entity
@Table(name = "roles")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;
     
    //Constructors
    
	public Role(RoleName name) {
		this.name = name;
    }
	
	
	public Role() {

    }
	
	
	//Methods

    /**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public RoleName getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(RoleName name) {
		this.name = name;
	}
	
	 

	@Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


	
}
