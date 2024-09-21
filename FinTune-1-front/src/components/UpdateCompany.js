import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import CompanyService from '../services/CompanyService';

const UpdateCompany = () => {

    const navigate = useNavigate();

    //cmpny id parameter
    const {cmp_id} = useParams();
    //once the page is rendered, we need to get the data from the database for that specific cmp_id
    const [company, setCompany]= useState({
        cmp_id: cmp_id,
        name: "",
        symbol:"",
        address: "",
        email: ""
    });

    const handleChange = (e) => {
        const val = e.target.value;
        setCompany({ ...company, [e.target.name]: val });
    };

    //use effect router dom to fetch the data and update the field
    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await CompanyService.getCompyById(company.cmp_id);
                //once we got the data from the API, we need to update the state
                setCompany(res.data);
            } catch (error) {
                console.log(error);
            }
        };
        fetchData();
    }, []);



    //update company
    const updateCompany = (e) => {
        e.preventDefault();
        console.log(company);
        CompanyService.updateCompany(company, cmp_id)
           .then((res) => {
            navigate("/companies");
           })
           .catch((error) => {
            console.log(error);
           });
    };


  return (
        <div className='flex max-w-2xl mx-auto shadow border-b'>
            <div className='px-8 py-8'>
                <div className='font-thin text-2xl tracking-wider'>
                    <h1 className=''>Edit Company</h1>
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
                    <button onClick={updateCompany} className='rounded text-whtite font-semibold bg-blue-400 py-2 px-6 hover:bg-blue-700'>Update</button>
                    <button 
                    onClick = {() => navigate("/companies")}
                    className='rounded text-whtite font-semibold bg-yellow-400 py-2 px-6 hover:bg-blue-700'>Cancel</button>
                </div>
        </div>
    </div>
  );
};

export default UpdateCompany;