import React, { useState } from 'react'
import { Navigate, NavLink } from 'react-router-dom'
import { toast, ToastContainer } from 'react-toastify'
import { parseJwt, logError } from '../../../utils/ApiErrorHelpers'

import { useAuth } from '../../../components/context/AuthentContext'
 import '../../../App.css'



//import User from '../../../components/User'
import AuthService from '../../../services/auth/AuthService'
import Header from '../../../components/Header'

const register = () => {
  //const img = new Image();
  const Auth = useAuth()
  //const isLoggedIn = Auth.isUserAuthenticated()
  const [registrationSuccess, setRegistrationSuccess] = useState(false);


  //useAuth.isAuthenticated;

 
  const [user, setUser] = useState({
    id: "",
    username: "",
    email: "",
    password: "",
    full_name: "",
    phone: ""
  });

  const [Error, setError] = useState(false)
  const [message, setMessage] = useState('')


  const handleChange = (e) => {
    const val = e.target.value;
    setUser({...user, [e.target.name]: val})
  };

  //check fields are empty
  if (!(user)) {
    setError(true)
    setMessage('All fields are required!')
    return
  }

  const onSubmit = async () => {
    try {
      await AuthService.Signup(user)
      .then(res => {
        if (res.status === 200) {
          const { accessToken } = res.data
          const data = parseJwt(accessToken)
          setUser(res.data)
       
          const authenticatedUser = {data, accessToken}
          Auth.login(authenticatedUser)
        }
       
       

       //setUser('')
       //setError(false)
       //setMessage('')
       
      });
    } catch (error) {
      logError(error)
      if (error.response && error.response.data) {
        const errData = error.response.data
        let errMsg = 'Invalid fields'
        if (errData.status === 409) {
          errMsg = errData.message
          toast.error(errMsg, {
            style: {
              border: '1px solid #f21505',
              padding: '16px',
              color: '#14b8a5',
            },
            iconTheme: {
              primary: '#f21505',
              secondary: '#FFFAEE',
            },
          })
        } else if (errData.status === 400) {
          errMsg = errData.errors[0].defaultMessage
          toast.error(errMsg, {
            style: {
              border: '1px solid #14b8a5',
              padding: '16px',
              color: '#14b8a5',
            },
            iconTheme: {
              primary: '#14b8a5',
              secondary: '#FFFAEE',
            },
          })
          setError(true)
          setMessage(errMsg)
        }
    }
  }
  setRegistrationSuccess(true)
  };



  if (registrationSuccess) {
    return (
      <div className='centered-container'>
        <div className="login-container">
        <h1>Registration successfull!</h1>
        <h2> </h2>
        <div className="registration-link">
          <p>To access your new account <a href="/signin">Login here</a></p>
        </div>
        </div>
  
      </div>);
  }

  return (
    <div className='bg-center mx-auto min-h-screen w-full items-end justify-center'>
      <Header />
      <ToastContainer position='top-center'/>
      
      <div class="min-h-screen bg-gray-100 text-gray-900 flex justify-center">
          <div class="max-w-screen-xl m-0 sm:m-10 bg-white shadow sm:rounded-lg flex justify-center flex-1">
              <div class="lg:w-1/2 xl:w-5/12 p-6 sm:p-12">
                <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                <img
                  alt="Yetai"
                  src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600"
                  className="mx-auto h-10 w-auto"
                />
                <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
                  Create an account
                </h2>
              </div>
              <br/><br/>
              <div className='mt-3 w-full rounded-lg bg-white'>
                <div className='relative bg-inherit'>
                <label className='text-gray-600 absolute -top-2 left-0 mx-8 ml-6 cursor-text bg-inherit px-2.5 text-xs'>Username</label>
                <input 
                  type="text" 
                  name='username'
                  value={user.username}
                  onChange={(e) => handleChange(e)}
                  placeholder='' 
                  className='h-8 w-96 border py-6 indent-8 rounded focus:ring-2 focus:ring-inset focus:ring-indigo-600'></input>
                </div>
              </div>
              <div className='mt-3 w-full rounded-lg bg-white'>
                <div className='relative bg-inherit'>
                  <label className='text-gray-600 absolute -top-2 left-0 mx-8 ml-6 cursor-text bg-inherit px-2.5 text-xs'>Email</label>
                  <input 
                    type="email" 
                    name='email'
                    value={user.email}
                    onChange={(e) => handleChange(e)}
                    placeholder='' 
                    className='h-8 w-96 border py-6 indent-8 rounded focus:ring-2 focus:ring-inset focus:ring-indigo-600'></input>
                </div>
              </div>
              <div className='mt-3 w-full rounded-lg bg-white'>
                <div className='relative bg-inherit'>
                  <label className='text-gray-600 absolute -top-2 left-0 mx-8 ml-6 cursor-text bg-inherit px-2.5 text-xs'>Password</label>
                  <input 
                    type="password" 
                    name='password'
                    value={user.password}
                    onChange={(e) => handleChange(e)}
                    placeholder='' 
                    className='h-8 w-96 border py-6 indent-8 rounded focus:ring-2 focus:ring-inset focus:ring-indigo-600'></input>
                </div>
              </div>
              <div className='mt-3 w-full rounded-lg bg-white'>
                <div className='relative bg-inherit'>
                  <label className='text-gray-600 absolute -top-2 left-0 mx-8 ml-6 cursor-text bg-inherit px-2.5 text-xs'>FullName</label>
                  <input 
                    type="text" 
                    name='full_name'
                    value={user.full_name}
                    onChange={(e) => handleChange(e)}
                    placeholder='' 
                    className='h-8 w-96 border py-6 indent-8 rounded focus:ring-2 focus:ring-inset focus:ring-indigo-600'></input>
                </div>
              </div>
              <div className='mt-3 w-full rounded-lg bg-white'>
                <div className='relative bg-inherit'>
                  <label className='text-gray-600 absolute -top-2 left-0 mx-8 ml-6 cursor-text bg-inherit px-2.5 text-xs'>Phone Number</label>
                  <input 
                    type="phone" 
                    name='phone'
                    value={user.phone}
                    onChange={(e) => handleChange(e)}
                    placeholder='' 
                    className='h-8 w-96 border py-6 indent-8 rounded focus:ring-2 focus:ring-inset focus:ring-indigo-600'></input>
                </div>
              </div>

              <div className='items-center justify-center h-14 w-full my-4 space-x-4 mt-6'>
                <button onClick={() => onSubmit()} 
                  className='flex w-96 rounded text-white font-semibold justify-center bg-indigo-600 py-2.5 px-6 hover:bg-indigo-500'>Register</button>
                <p className="text-center pt-5">
                    Already have an account?{" "}
                    <NavLink to="/signin" as={NavLink}>
                      <span className="font-semibold underline decoration-teal-600 cursor-pointer">
                        Login!
                      </span>
                    </NavLink>
                  </p>
              </div>
              </div>
              <div class="flex-1 bg-indigo-100 text-center hidden lg:flex">
                  <div class="w-full bg-contain bg-center bg-no-repeat"
                      style={{background: "url('../../../assets/bg2.png')"}}
                      >
                  </div>
              </div>
          </div>
      </div>
  </div>
  )
}

export default register