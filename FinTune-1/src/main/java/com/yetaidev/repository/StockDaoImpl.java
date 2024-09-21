package com.yetaidev.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yetaidev.dto.StockResponse;
import com.yetaidev.model.StockPrices;
import com.yetaidev.utils.DataSourceImp;
import com.yetaidev.utils.DbQuery;

@Service
public class StockDaoImpl implements StocksDao{
	DataSourceImp ds = new DataSourceImp();
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(ds.getDataSource());
	



	protected final Log logger = LogFactory.getLog(getClass());
	
	//importing our commit class here to create a new commit instance to save stock prices data
	//a query instance
	
	DbQuery dq = new DbQuery();
	
		
	
	/*
	 * INSERT STOCK DETA METHOD
	 */
	@Override
	public int saveStock(StockPrices stockPrice) throws SQLException {
						
		int res;
		String query = dq.insertStock();
		
		//handle SQL update operation(insert)
		logger.info(":::::: Executing stock insert query ::::::" +jdbcTemplate);
		logger.info(":::::: Executing stock insert query ::::::" +query);
		
				
		Object[] arg = {
				stockPrice.getSymbol(),
				stockPrice.getDate(), 
				stockPrice.getOpen(), 
				stockPrice.getHigh(), 
				stockPrice.getLow(), 
				stockPrice.getClose(), 
				stockPrice.getVolume()};
		res = jdbcTemplate.update(query, arg);
		return res;
	}
				

	
	//find stock by id
	@Override
	public StockPrices findById(String symbol) {
		try {
			String query = dq.findStockById();
			StockPrices stock = jdbcTemplate.queryForObject(query, 
					BeanPropertyRowMapper.newInstance(StockPrices.class), symbol);
			return stock;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//delete local stock by ID
	@Override
	public int deleteById(int stock_id) {
		String query = dq.delStockById();
		return jdbcTemplate.update(query, stock_id);
	}
	
	
	//list all stocks
	@Override
	public List<StockResponse> findAll() throws SQLException {
		String query = dq.findAllStock();
		return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(StockResponse.class));
	}
	

	@Override
	public StockPrices findBySymbol(String symbol) {
		try {
			String query = dq.findStockById();
			StockPrices stock = jdbcTemplate.queryForObject(query, 
					BeanPropertyRowMapper.newInstance(StockPrices.class), symbol);
			return stock;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//delete local stock by symbol
	@Override
	public int deleteBySymbol(String symbol) {
		String query = dq.delStockBySymbol();
		return jdbcTemplate.update(query, symbol);
	}


	//delete all stock
	@Override
	public int deleteAllStocks() {
		String query = dq.dellAllStocks();
		int update = jdbcTemplate.update(query);
		
		return update;
	}
	

	/*
	 * END STOCK DETA METHOD
	 */

}
