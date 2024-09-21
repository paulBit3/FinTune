package com.yetaidev.api.integration;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.patriques.AlphaVantageConnector;
import org.patriques.TimeSeries;
import org.patriques.input.timeseries.Interval;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.data.StockData;

import io.github.mainstringargs.alphaVantageScraper.AlphaVantageAPIKey;

import lombok.extern.slf4j.Slf4j;


import com.yetaidev.model.StockPrices;
import com.yetaidev.repository.*;
import com.yetaidev.vendor.exception.AlphaVantageException;


/**
 * this class handle the 3rd party API call service
 * @param <IntraDay>
 */
@Slf4j
public class AlphaVantageAPIStockService {
	//remove an element from an array
	public static String[] remove(String[] elements, String mySymbol) {
		String[] newAr = new String[elements.length-1];
		int index = 0;
		for (int i=0; i< elements.length; i++) {
			if(elements[i] != mySymbol) {}
			newAr[index] = elements[i];
			index++;
		}
		return newAr;
		
	}
	
	//remove string from stringbuilder
	private static void delete(StringBuilder sb, String s) {
		//get the index of the string element
		int start = sb.indexOf(s);
		//if the undex less than 0 return
		if (start < 0)
			return;
		sb.delete(start, start + s.length());
	}

	 /*
	  * GET STOCK PRICES METHOD
	  */
		
		//method to get stock data. It will throw an exception
		public static void getStockData() {
			
			//StockPricesDao stockRepo;
			StocksDao stockRepo = new StockDaoImpl() ;
			
			
			//API token
			String apiKey = AlphaVantageAPIKey.getAPIKey();
			int timeout = 3000;
			
			//api connector
			AlphaVantageConnector apiConnector;
			
			//stock time series to get daily stock
			TimeSeries stockTS;

			//connector instance
			apiConnector = new AlphaVantageConnector(apiKey, timeout);
			
			//stoock time serie instance. we pass API connexion
			stockTS = new TimeSeries(apiConnector);
			
			//a companies symbols array
			String[] symbolss = new String[]{"AAPL", "AMZN", "GOOG", "WMT", "META", "IBM", "MSFT"};
			StringBuilder mySB = new StringBuilder();
			
			
			/*
			 * returns intraday time series (timestamp, open, high, low, close, volume) 
			 * of the equity specified, updated realtime. We added 5 min as interval 
			 * two data points in the time series
			 * 
			 */

			int n2 = symbolss.length-1;
			String[] newArr = new String[n2];
			String sep = "";
			int idx = 0;
			
			//loop through the symbol list
			for (String str : symbolss) {
				mySB.append(sep).append(str);

				log.info(""+ mySB.toString());
								
				IntraDay response = stockTS.intraDay(mySB.toString(), Interval.FIVE_MIN, OutputSize.COMPACT);
				//log.info(""+ response);
				Map<String, String> stockMetaData = response.getMetaData();
				
				log.info("Information: " + stockMetaData.get("1. Information"));
				log.info("Stock: " + stockMetaData.get("2. Symbol"));
				log.info("Last Refreshed: " + stockMetaData.get("3. Last Refreshed"));
				
				//a list of stock data
				List<StockData> stockData = response.getStockData();
				//formatting local datetime 
				//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
				String fDate = "";
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				
				try {
					//retrieve each value provided
					for(StockData stock : stockData) {
						
						String symbol = stockMetaData.get("2. Symbol");
						
						LocalDateTime date = stock.getDateTime();
						fDate = date.format(dtf); //formatted the date to dd-MM-yyyy HH:mm
						//LocalDate mDate = LocalDate.parse(fDate);
						
						Double open = stock.getOpen();
						Double high = stock.getHigh();
						Double low = stock.getLow();
						Double close = stock.getClose();
						long volume = stock.getVolume();
						
						StockPrices stockPrice = StockPrices.builder()
								.symbol(symbol)
								.date(date.toLocalDate())
								.open(open)
								.high(high)
								.low(low)
								.close(close)
								.volume(volume)
								.build();  
						
						
						log.info("symbol:" + symbol);
						log.info("date:" + fDate);
						log.info("open:" + open);
						log.info("high:" + high);
						log.info("low:" + low);
						log.info("close:" + close);
						log.info("volume:" + volume);
						
						//insert stockData in the database
						int i = 0;
						try {
							i = stockRepo.saveStock(stockPrice);
							delete(mySB, mySB.toString());
							//remove(symbolss, mySB.toString());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch(AlphaVantageException e) {
					//throw new AlphaVantageException(e);
					System.out.println(e);
				}
				//return;
				
				//mySymbol.add(symbol);
			}
			//IntraDay response = stockTS.intraDay("", Interval.FIVE_MIN, OutputSize.COMPACT);
			//IntraDay response = stockTS.intraDay(getSymbol(symbols), Interval.FIVE_MIN, OutputSize.COMPACT);
			 //stockTS.intraDay("IBM", Interval.TEN_MIN, OutputSize.COMPACT);
			
			
			
			
	
		
			

			//return null;
		}
		
		/*
		 * END GET STOCK DETA METHOD
		 */

}
