import React, { useEffect , useState} from 'react'
import { useNavigate } from 'react-router-dom'

import Header from './Header';
import StockService from '../services/StockService';
import StockPrices from './StockPrices';


const StockList2 = () => {
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
export default StockList2;