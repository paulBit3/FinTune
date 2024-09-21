import React, { useState } from 'react'
import { Navigate, Link, redirect } from 'react-router-dom'
import { toast, ToastContainer } from 'react-toastify'

//import User from '../../../components/User'
import AuthService from '../../../services/auth/AuthService'
import Header from '../../../components/Header'
import { useAuth } from '../../../components/context/AuthentContext'

import {parseJwt, logError } from '../../../utils/ApiErrorHelpers'


const login = () => {

  const Auth = useAuth()

  //const isLoggedIn = Auth.isAuthenticated()

  const [Error, setError] = useState(false)
  //const navigate = useNavigate();

  
  const [user, setUser] = useState({
    email: "",
    password: ""
  });

  const userIsAuthenticated = () =>{
    let storedUser = localStorage.getItem('user');
    if (!storedUser) {
        return false;
    }
    storedUser = JSON.parse(storedUser);

    //if token expired, logout user
    if (Date.now() > storedUser.data.exp * 1000) {
        Auth.signout();
        return false;
    }
    return true;
}


  const handleChange = (e) => {
    const val = e.target.value;
    setUser({...user, [e.target.name]: val})
  };


  //const url = `?email=${encodeURIComponent(user.email)}&password=${encodeURIComponent(user.password)}`;
  const onSubmit = async () => {
    if (!user) {
      setError(true)
      return
    }
    try {
      await AuthService.Signin(user.email, user.password)
      .then(res => {
        setUser(res.data)

        const { accessToken } = res.data
        const data = parseJwt(accessToken)
        const authenticatedUser = {data, accessToken}

        Auth.login(authenticatedUser)

        setUser('')
        setError('')
        redirect("/")
      })
    } catch (error) {
      logError(error)
      setError(true)
    }
      
  }

  if(userIsAuthenticated()) {
    return <Navigate to={'/'} /> 
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
                  Sign In
                </h2>
              </div>
              <br/><br/>
              {/* {hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}
              {showSuccessMessage && <div>Login Sucessful</div>} */}
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
              <div className='items-center justify-center h-14 w-full my-4 space-x-4 mt-6'>
                <button onClick={onSubmit} 
                  className='flex w-96 rounded text-white font-semibold justify-center bg-indigo-600 py-2.5 px-6 hover:bg-indigo-500'>Login</button>
                 <p className="pt-6 pb-1 text-center text-gray-500">You don't have an account? </p>
                  <p className="mb-2 text-center">
                    <span className="font-semibold underline decoration-2 decoration-teal-600 cursor-pointer">
                      <a href='/register'>Sign Up Now!</a>
                    </span>
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

export default login