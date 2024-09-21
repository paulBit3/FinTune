import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import CompanyService from '../services/CompanyService';



const Company = () => {

  const [company, setCompany] = useState({
    cmp_id: "",
    name: "",
    symbol:"",
    address: "",
    email: ""
  })

  const navigate = useNavigate();

  const handleChange = (e) => {
    const val = e.target.value;
    setCompany({ ...company, [e.target.name]: val });
  };

  const saveCompany = (e) => {
    e.preventDefault();
    //calling the company service, and pass company object
    CompanyService.saveCompany(company)
      .then(res => {
        //console.log(res.data);
        setCompany(res.data)
        //calling company list here
        navigate("/companies");
      }).catch(error => {
        if (error.response) {
          // The server responded with a status code outside the 2xx range
          console.log('Error response:', error.response);
        } else if (error.request) {
          // The request was made but no response was received
          console.log('Error request:', error.request);
        } else {
          // Something happened in setting up the request that triggered an error
          console.log('Error message:', error.message);
        }
      });
  }

  //reseting the form
  const reset = (e) => {
    e.preventDefault();
    setCompany(
      {
        cmp_id: "",
        name: "",
        symbol:"",
        address: "",
        email: ""
      }
    );
  }

 
  return (
    <div className='flex max-w-2xl mx-auto shadow border-b'>
      <div className='px-8 py-8'>
        <div className='font-thin text-2xl tracking-wider'>
          <h1 className=''>Add New Company</h1>
        </div>
        <div className='items-center justify-center h-14 w-full my-4'>
          <label className='block text-gray-600 text-sm font-normal'>Name</label>
          <input 
            type="text" 
            name='name'
            value={company.name}
            onChange={(e) => handleChange(e)}
            placeholder='company name' 
            className='h-8 w-96 border mt-2 px-2 py-2'></input>
        </div>

        <div className='items-center justify-center h-14 w-full my-4'>
          <label className='block text-gray-600 text-sm font-normal'>Symbol</label>
          <input 
            type="text" 
            name='symbol'
            value={company.symbol}
            onChange={(e) => handleChange(e)}
            placeholder='company symbol' 
            className='h-8 w-96 border mt-2 px-2 py-2'></input>
        </div>

        <div className='items-center justify-center h-14 w-full my-4'>
          <label className='block text-gray-600 text-sm font-normal'>Address</label>
          <input 
            type="text" 
            name='address'
            value={company.address}
            onChange={(e) => handleChange(e)}
            placeholder='company address' 
            className='h-8 w-96 border mt-2 px-2 py-2'></input>
        </div>

        <div className='items-center justify-center h-14 w-full my-4'>
          <label className='block text-gray-600 text-sm font-normal'>Email</label>
          <input 
            type="email" 
            name='email'
            value={company.email}
            onChange={(e) => handleChange(e)}
            placeholder='company email' 
            className='h-8 w-96 border mt-2 px-2 py-2'></input>
        </div>

        <div className='items-center justify-center h-14 w-full my-4 space-x-4 mt-6'>
          <button onClick={saveCompany} className='rounded text-white font-semibold bg-indigo-600 py-2 px-6 hover:bg-indigo-500'>Save</button>
          <button 
          onClick = {reset}
          className='rounded text-white font-semibold bg-yellow-400 py-2 px-6 hover:bg-gray-400 focus-visible:outline-indigo-600 focus-visible:outline-2 focus-visible:outline-offset-2 '>Clear</button>
        </div>
      </div>
    </div>
  )
}

export default Company;