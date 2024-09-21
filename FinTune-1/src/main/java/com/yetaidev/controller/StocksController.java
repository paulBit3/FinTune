package com.yetaidev.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yetaidev.dto.StockResponse;
import com.yetaidev.model.StockPrices;
import com.yetaidev.repository.StocksDao;

import lombok.extern.slf4j.Slf4j;


/**
 * This is the Stock controller class. 
 * It has a method DbQuery.sqlInsert() to commit stock data into our db
 * Spring JDBC allows us to write your SQL queries explicitly, 
 * giving us complete control over the database interactions.
 * 
 * NO POST ENDPOINT FOR STOCK TABLE AS WE PULL UP DATA THROW 3RD PARTY API
 * AND SAVE IT IN OUR LOCAL DATABASE USING JDBCTEMPLATE
 * WE WILL JUST HAVE A GET ENDPOINT TO PULL DATA FROM OUR LOCAL DATABASE
 */


@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/")
public class StocksController {
	// stock price dao
	@Autowired
	private StocksDao stockDao;

			

		
	//GET ENDPOINT to get stock Data from Vendor Alpha Vantage
		  
	//@ResponseStatus(HttpStatus.OK)
	@GetMapping("/stocks")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	List<StockResponse> getAllStockData() throws SQLException{
		log.info("Get all stock");
		return stockDao.findAll();
	}
		
	//get stock by company symbol
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("stock/{symbol}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public StockPrices getStockBySymbol(@PathVariable String symbol) {
		try {
			return stockDao.findBySymbol(symbol);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
		
		

	/*
	 * DELETE ENDPOINT
	 */
	//delete stock by symbol. I use ResponseEntity to easily manipulate Http response
	@DeleteMapping("stock/{symbol}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity delStockBySymbol(@PathVariable String symbol) {
		stockDao.deleteBySymbol(symbol);
		log.info("Stock was deleted successfully.", HttpStatus.OK);
		return ResponseEntity.ok().build();
			
	}
		
		
		
	//delete stock by symbol. I use ResponseEntity to easily manipulate Http response
	//@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("stock/{stock_id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity delStockById(@PathVariable int stock_id) {
		stockDao.deleteById(stock_id);
		log.info("Stock Id {} was deleted successfully.", stock_id, HttpStatus.OK);
		return ResponseEntity.ok().build();

		
	}	

}
