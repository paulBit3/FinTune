import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import CompanyService from '../services/CompanyService';
import CompanySep from './CompanySep';

const CompanyList = () => {

    const navigate = useNavigate();

    const [loading, setLoading] = useState(true);
    const [companies, setCompanies] = useState([]);

    //fetching company data
    useEffect(() => {
        const fetchData = async () => {
            //set loading to true
            setLoading(true);
            try {
                //we use await here to wait for data
                const res = await CompanyService.getCompanies();

                //we wait till we got response here
                setCompanies(res.data);
            } catch(error) {
                console.log(error);
            }
            //set loadin to false
            setLoading(false);
        };
        fetchData();
    }, []);

    //this function call company template to delete company in the db
    const deleteCompany = (e, cmp_id) => {
        e.preventDefault();
        CompanyService.deleteCompany(cmp_id).then((res) => {
            if (companies) {
                setCompanies((prevElement) => {
                    //filtering the element(company)
                    return prevElement.filter((company) => company.cmp_id !== cmp_id);
                });
            }
        });
    };

  return (
    <>
        <div className='container mx-auto my-8'>
            <div className='h-12'>
                <button 
                    onClick={() => navigate("/company")}
                    className='rounded bg-slate-600 text-white px-6 py-2 font-semibold'>
                    Add Company
                </button>
            </div>
            <div className='flex shadow border-b'>
                <table className='min-w-full'>
                    <thead className='bg-gray-50'>
                        <tr>
                            <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>id</th>
                            <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>name</th>
                            <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>symbol</th>
                            <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>address</th>
                            <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>email</th>
                            <th className='text-right font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>actions</th>
                        </tr>
                    </thead>

                    {!loading && (
                        <tbody className='bg-white'>
                            {companies.map((company) => (
                                <CompanySep 
                                    company={company}
                                    deleteCompany = {deleteCompany} 
                                    key={company.cmp_id}></CompanySep>
                            ))}
                        </tbody>
                    )}
                </table>
            </div>
        </div>
    </>
  );
};

export default CompanyList;