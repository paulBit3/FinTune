🤔About the Project:
------

 The system provides users with real-time stock prices data, allows them to monitor 
       and manage their stock portfolios, and offer insights for informed investment decisions and coaching.

Technology Stack:
----



------
Backend(Java) & API: 
  - Java,
  - Spring Boot,
  - Spring Data JPA,
  - Spring Security,
  - MySQL,
  - AlphaVantage API integration(3rd party financial API)

Frontend(React):
  - React,
  - Axios(HTTP request),
  - Tailwind CSS,
  - JavaScript,
  - HTML

Architecture(Microservices):
  - The application components communicate via API.
  - The app use RESTful API, for communication between the React frontend and the Java backend

------
Satisfied these Core features:
---
User Authentication & Profiles:
- Users can register, login, manage their profiles, and logout.
- The app store users info such as investment goals, expenses habits, etc..

Coaching Session Booking:
- Users can book a Coaching Sessions with Certified investment coaches
- The app integrated Scheduling system for appointments
- The app integrated a video conference feature for live sessions
  
Subscription & Plan:
- Users can subscribe to a plan to buy stock or sell stock
- Users can subscribe to a plan for an investment coaching session. 
- Users can buy or sell stock(still implementing).

Investment Portfolio Tracking:
- Users can track their investment portfolios
- The app integrated Vizualisations and Analytics for Portfolio performance

Investment Community Forum:
- A space where users can interact, ask questions, and share insights their investment portfolios
  
Educational Resources:
- The app offers educational content such as articles, videos, and tutorials for investment strategies.

Clone or Download the zip  ```git clone: https://github.com/paulBit3/FinTune.git```

UI/UX of the Project:
------

![Screenshot 2024-09-29 195044](https://github.com/user-attachments/assets/bc95d5f2-8a17-4408-a4cf-1dbc9065a081)

![Screenshot 2024-09-09 193633](https://github.com/user-attachments/assets/eb309e93-4ff0-4e96-8ae5-cba7e235eda1)

![Screenshot 2024-09-09 194223](https://github.com/user-attachments/assets/0a959936-d262-4616-bb51-c2724805d439)

![Screenshot 2024-09-09 192904](https://github.com/user-attachments/assets/4a5c6acf-1996-4b5e-8008-71d9c5a2e789)

![Screenshot 2024-09-09 194414](https://github.com/user-attachments/assets/3c82f203-ba97-463d-8f15-43fa2559f22f)

![Screenshot 2024-09-21 172619](https://github.com/user-attachments/assets/02e92bc9-b310-447e-ac11-4c211e1d9dae)

![Screenshot 2024-09-09 194511](https://github.com/user-attachments/assets/d7a965cb-e365-4b26-b64e-0edfab8b2443)






Code snippet ----

```Java
//method to get stock data

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
			
			//loop through the companies symbol list and pick each company symbol to grab its stock data
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
			}

		}
```

- StockPrices
 
```JSX
import React from 'react'
import { useNavigate } from 'react-router-dom';

import { formatCurrency } from './formatCurrency';

/** in this component, we separate stock map here instead of putting all in stock list. this 
 * makes our code more cleaner
 */

const StockPrices = ({ stock }) => {
  const navigate = useNavigate();

  //buy stock function
  const buyStock = (e, stock_id) => {
    e.preventDefault();
    //navigate to buy stock page
    navigate(`/buyStock/${stock_id}`);
  };

  //sell stock function
  const sellStock = (e, stock_id) => {
    e.preventDefault();
    //navigate to buy stock page
    navigate(`/sellStock/${stock_id}`);
  };

 


  return (
      <tr key = {stock.stock_id}>
      <td className='text-left px-6 py-4 whitespace-nowrap'>
          <div className='text-sm text-gray-500'>{stock.stock_id}</div>
      </td>
      <td className='text-left px-6 py-4 whitespace-nowrap symbol'>
          <div className='text-sm text-gray-500'>{stock.symbol}</div>
      </td>
      <td className='text-left px-6 py-4 whitespace-nowrap'>
          <div className='text-sm text-gray-500'>{stock.date}</div>
      </td>
      <td className='text-left px-6 py-4 whitespace-nowrap'>
          <div className='text-sm text-gray-500'>{formatCurrency(stock.open)}</div>
      </td>
      <td className='text-left px-6 py-4 whitespace-nowrap'>
          <div className='text-sm text-gray-500'>{formatCurrency(stock.high)}</div>
      </td>
      <td className='text-left px-6 py-4 whitespace-nowrap'>
          <div className='text-sm text-gray-500'>{formatCurrency(stock.low)}</div>
      </td>
      <td className='text-left px-6 py-4 whitespace-nowrap'>
          <div className='text-sm text-gray-500'>{formatCurrency(stock.close)}</div>
      </td>
      <td className='text-left px-6 py-4 whitespace-nowrap'>
          <div 
            className='text-sm text-gray-500'
            style={{color:
                stock.volume < 0
                ? "red"
                : stock.volume > 0
                ? "lightgreen"
                : "auto"
             }}
          >{formatCurrency(stock.volume)}</div>
      </td>
      <td className='text-right px-6 py-4 whitespace-nowrap font-medium text-sm'>
          <a onClick={(e, stock_id) => buyStock(e, stock.stock_id)}
              className='rounded text-white font-semibold bg-indigo-600 py-2 px-6 hover:bg-indigo-500 hover:cursor-pointer'>
              Buy
          </a>
          <a
              onClick = {(e, stock_id) => sellStock(e, stock.stock_id)}
              className='rounded text-white font-semibold bg-yellow-400 py-2 px-6 hover:bg-indigo-500 hover:cursor-pointer'>
              Sell
          </a>
      </td>
  </tr>
  );
};

export default StockPrices;
```

- StockList
```JSX
import React, { useEffect , useState} from 'react'
import { useNavigate } from 'react-router-dom'

import Header from './Header';
import StockService from '../services/StockService';
import StockPrices from './StockPrices';


const StockList = () => {
    const navigate = useNavigate();

    const [loading, setLoading] = useState(true);
    const [stocks, setStocks] = useState([]);



    useEffect(() => {
        const getStock = async () => {
            setLoading(true)
            try {
                //we use await here to wait for stock data
                const res = await StockService.getStockData();
                setStocks(res.data) ;
            } catch (error) {
                console.log(error)
            }
            //set loadin to false
            setLoading(false);
        };
        getStock();
    }, [])

    return (
        <>  <div className=''>
                <Header />
              <div className='container mx-auto my-8'>
                    <div className='h-12'>
                        <p className='bg-slate-600 text-white px-6 py-2 font-semibold'>Stocks list</p>
                    </div>
                    <div className='h-10 text-right'>
                        <a className='text-right text-indigo-600 hover:text-indigo-800 px-4 hover:cursor-pointer'
                         onClick = {() => navigate("/companies")}>View <span aria-hidden="true" class="ml-1">→</span></a>
                    </div>
                    <div className='flex shadow border-b'>
                        <table className='border border-gray-700 w-full text-left'>
                            <thead className='bg-gray-50'>
                                <tr>
                                    <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>id</th>
                                    <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>symbol</th>
                                    <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>date</th>
                                    <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>open</th>
                                    <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>high</th>
                                    <th className='text-right font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>low</th>
                                    <th className='text-right font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>close</th>
                                    <th className='text-right font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>volume</th>
                                    <th className='text-right font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>actions</th>
                                </tr>
                            </thead>
                           
        
                            
                              {!loading && (   
                                 <tbody className='bg-white'>
                                      {stocks.map((stock) => (
                                          <StockPrices
                                              stock={stock}
        
                                              key={stock.stock_id}></StockPrices>
                                      ))}
                                  </tbody>
                            )}
                        </table>
                    </div>
                </div>
            </div>
        </>
    );
}
export default StockList;
```


