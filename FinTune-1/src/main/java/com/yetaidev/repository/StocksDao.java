package com.yetaidev.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yetaidev.dto.StockResponse;
import com.yetaidev.model.StockPrices;


@Repository
public interface StocksDao {
	int saveStock(StockPrices stockPrice) throws SQLException;

	StockPrices findById(String symbol);

	int deleteById(int stock_id);

	List<StockResponse> findAll() throws SQLException;

	StockPrices findBySymbol(String symbol);

	int deleteBySymbol(String symbol);
	
	int deleteAllStocks();
}
