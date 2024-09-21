import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';

const Hero = () => {
    const navigate = useNavigate();
    const isAuthenticated = useState(true);

  return (
    <div className='py-20 bg-indigo-400'>
        <div className='container mx-auto px-6'>
            <h2 className='text-4xl font-bold mb-2 text-white'>
                Real Time Stock Prices Monitoring Platform!
            </h2>
            <h3 className='text-2xl mb-8 text-gray-200'>
                Stay free in mind! Monitor stock prices anywhere you go.
            </h3>
            {isAuthenticated &&
            <button
                onClick={() => navigate("/stocks")}
                className='bg-white font-bold rounded-full py-2 px-4 shadow-lg uppercase tracking-wider'
            >
                Check Stocks<span aria-hidden="true" class="ml-1">→</span>
            </button>}
            
        </div>
    </div>
  );
};

export default Hero;