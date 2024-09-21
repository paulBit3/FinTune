package com.yetaidev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.yetaidev.api.integration.AlphaVantageAPIStockService;




@SpringBootApplication
public class FinTune1Application {

	public static void main(String[] args) {
		SpringApplication.run(FinTune1Application.class, args);
		
		//calling our external API here
		//AlphaVantageAPIStockService.getStockData();
	}

}
