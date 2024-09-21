/**
 * 
 */
package com.yetaidev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yetaidev.model.Companies;

/**
 * Companies data access object class
 */
@Repository
public interface CompaniesDao extends JpaRepository<Companies, Long> {

}
