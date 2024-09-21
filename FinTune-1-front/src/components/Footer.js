import React from 'react'

const Footer = () => {
  return (
    <footer className='bg-gray-100'>
        <div className='container mx-auto px-6 pt-10 pb-6'>
            <div className='flex flex-wrap'>
                <div className='w-full md:w-1/4 text-center md:text-left'>
                    <h5 className='uppercase mb-6 font-bold'>Accounts</h5>
                    <ul className='mb-4'>
                        <li className='mt-2'>
                            <a href='#'
                                className='hover:underline text-gray-700 hover:text-orange-500'
                            >Brokarage Account</a>
                        </li>
                        <li className='mt-2'>
                            <a href='#'
                                className='hover:underline text-gray-700 hover:text-orange-500'
                            >Saving Account</a>
                        </li>
                        <li className='mt-2'>
                            <a href='#'
                                className='hover:underline text-gray-700 hover:text-orange-500'
                            >Customer Support</a>
                        </li>
                    </ul>
                </div>
                <div className='w-full md:w-1/4 text-center md:text_left'>
                    <h5 className='uppercase mb-6 font-bold'>Invest</h5>
                    <ul className='mb-4'>
                        <li className='mt-2'>
                            <a href='#'
                                className='hover:underline text-gray-700 hover:text-orange-500'
                            >Smart Investing</a>
                        </li>
                        <li className='mt-2'>
                            <a href='#'
                                className='hover:underline text-gray-700 hover:text-orange-500'
                            >Self Investing</a>
                        </li>
                        <li className='mt-2'>
                            <a href='#'
                                className='hover:underline text-gray-700 hover:text-orange-500'
                            >Stocks</a>
                        </li>
                        <li className='mt-2'>
                            <a href='#'
                                className='hover:underline text-gray-700 hover:text-orange-500'
                            >Buy & Sell</a>
                        </li>
                        <li className='mt-2'>
                            <a href='#'
                                className='hover:underline text-gray-700 hover:text-orange-500'
                            >Mobile Banking</a>
                        </li>
                    </ul>
                </div>
                <div className='w-full md:w-1/4 text-center md:text_left'>
                    <h5 className='uppercase mb-6 font-bold'>Term & Conditions</h5>
                    <ul className='mb-4'>
                        <li className='mt-2'>
                            <a href='#'
                                className='hover:underline text-gray-700 hover:text-orange-500'
                            >Privacy Policy</a>
                        </li>
                        <li className='mt-2'>
                            <a href='#'
                                className='hover:underline text-gray-700 hover:text-orange-500'
                            >Data Disclaimer</a>
                        </li>
                        <li className='mt-2'>
                            <a href='#'
                                className='hover:underline text-gray-700 hover:text-orange-500'
                            >Status</a>
                        </li>
                    </ul>
                </div>
                <div className='w-full md:w-1/4 text-center md:text_left'>
                    <h5 className='uppercase mb-6 font-bold'>Company</h5>
                    <ul className='mb-4'>
                        <li className='mt-2'>
                            <a href='#'
                                className='hover:underline text-gray-700 hover:text-orange-500'
                            >Our Story</a>
                        </li>
                        <li className='mt-2'>
                            <a href='#'
                                className='hover:underline text-gray-700 hover:text-orange-500'
                            >Contact</a>
                        </li>
                        <li className='mt-2'>
                            <a href='#'
                                className='hover:underline text-gray-700 hover:text-orange-500'
                            >Blog</a>
                        </li>
                        <li className='mt-2'>
                            <a href='#'
                                className='hover:underline text-gray-700 hover:text-orange-500'
                            >Careers</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>
  )
}

export default Footer;