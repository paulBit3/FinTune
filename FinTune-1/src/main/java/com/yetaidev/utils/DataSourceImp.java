package com.yetaidev.utils;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceImp {
	
	private final String url = "jdbc:mysql://localhost:3306/stock";
	private final String username = "stock";
	private final String password = "final123@";
	
	public DataSource getDataSource() {
		
		DataSource dataSource = new DriverManagerDataSource(url, username, password);
		
		return dataSource;
	}
}
