import React from 'react'
import { useNavigate } from 'react-router-dom';

/** in this component, we separate company map here instead of putting all in company list. this 
 * makes our code more cleaner
 */


const CompanySep = ({ company, deleteCompany }) => {
    //editing the page
   const navigate = useNavigate();
   const editCompany = (e, cmp_id) => {
    e.preventDefault();
    //navigate to edit route. we pass cmp_id as parameter
    navigate(`/editCompany/${cmp_id}`);
   };



  return (
    <tr key = {company.cmp_id}>
        <td className='text-left px-6 py-4 whitespace-nowrap'>
            <div className='text-sm text-gray-500'>{company.cmp_id}</div>
        </td>
        <td className='text-left px-6 py-4 whitespace-nowrap'>
            <div className='text-sm text-gray-500'>{company.name}</div>
        </td>
        <td className='text-left px-6 py-4 whitespace-nowrap'>
            <div className='text-sm text-gray-500'>{company.symbol}</div>
        </td>
        <td className='text-left px-6 py-4 whitespace-nowrap'>
            <div className='text-sm text-gray-500'>{company.address}</div>
        </td>
        <td className='text-left px-6 py-4 whitespace-nowrap'>
            <div className='text-sm text-gray-500'>{company.email}</div>
        </td>
        <td className='text-right px-6 py-4 whitespace-nowrap font-medium text-sm'>
            <a onClick = {() => navigate("/stocks")}
                className='text-indigo-600 hover:text-indigo-800 px-4 hover:cursor-pointer'>
                Stock prices
            </a>
            <a onClick={(e, cmp_id) => editCompany(e, company.cmp_id)}
                className='text-indigo-600 hover:text-indigo-800 px-4 hover:cursor-pointer'>
                Edit
            </a>
            <a
                onClick = {(e, cmp_id) => deleteCompany(e, company.cmp_id)}
                className='text-indigo-600 hover:text-indigo-800 hover:cursor-pointer'>
                Delete
            </a>
        </td>
    </tr>
  );
};

export default CompanySep;