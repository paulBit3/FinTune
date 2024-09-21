/**
 * 
 */
package com.yetaidev.utils;



/**After getting stock data from Alpha Vantage
 * this class manage sql queries to store stock data into our db
 */
//@Service
public class DbQuery {
	
	//my table stock prices where I will save alpha vantage stock info
	private static final String STOCK_PRICES_TABLE = "stock_prices";
	
	//private variables for queries
	
	protected static String ADD_STOCK;
	
	protected static String FIND_ALL_STOCK;
	
	protected static String FIND_STOCK_BY_ID;
	
	protected static String DEL_STOCK_BY_ID;
	
	protected static String DEL_STOCK_BY_SYMBOL;
	
	protected static String DEL_ALL_STOCK;
	
	/*
	 * M E T H O D S 
	 */
	
		//this method prepares sql insert to save stock data in the Db
	public String insertStock() {
		return ADD_STOCK = "INSERT INTO "
				+ STOCK_PRICES_TABLE + ""
				+ "(symbol, date, open, high, low, close, volume) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?)";
	}
	
	
	
	public String findAllStock() {
		return FIND_ALL_STOCK = "SELECT * FROM "
				+ STOCK_PRICES_TABLE + ""
				+ " ORDER BY symbol";
	}
	
	
	public String findStockById() {
		return FIND_STOCK_BY_ID = "SELECT * FROM "
				+ STOCK_PRICES_TABLE + ""
				+ " WHERE symbol = ?"
				+ " LIMIT 1";
	}
	
	public String delStockById() {
		return DEL_STOCK_BY_ID = " DELETE FROM "
				+ STOCK_PRICES_TABLE + ""
				+ " WHERE stock_id = ?";
	}
	
	public String delStockBySymbol() {
		return DEL_STOCK_BY_SYMBOL = " DELETE FROM "
				+ STOCK_PRICES_TABLE + ""
				+ " WHERE symbol = ?";
	}
	
	public String dellAllStocks() {
		return DEL_ALL_STOCK = " DELETE FROM "
				+ STOCK_PRICES_TABLE;
		
	}
	
	
	/*
	 * E N D  M E T H O D S 
	 */
}
