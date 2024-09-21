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