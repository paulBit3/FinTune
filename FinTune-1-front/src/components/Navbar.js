import React, {useState} from 'react'
import { useNavigate } from 'react-router-dom';

import { useAuth } from './context/AuthentContext';



const openTab = (url) => {
  window.open(url, "_self", "noreferrer");
};


const Navbar = () => {
  const [isLoggedin, setIsLoggedin] = useState(false);
  const navigate = useNavigate();
  const {getUser, signout } = useAuth()

  const userIsAuthenticated = () =>{
    let storedUser = localStorage.getItem('user');
    if (!storedUser) {
        return false;
    }
    storedUser = JSON.parse(storedUser);

    //if token expired, logout user
    if (Date.now() > storedUser.data.exp * 1000) {
        signout();
        return false;
    }
    return true;
}

//logout user method
const logout = () => {
  signout()
}

//authenticated menu style method
const signoutMenuStyle = () => {
  return userIsAuthenticated() ? { "display": "block" } : { "display": "none" }
}

const isUserAuthenticated = () => {
  return userIsAuthenticated()
}

//get the username of the authenticated user
const getUsername = () => {
  const user = getUser()
  return user ? user.data.username : ''
}

  return (
    <div className="bg-gray-800 mx-auto flex max-w-7xl items-center justify-between p-2 lg:px-6" >
      <div className='h-16 px-8 flex items-center'>
        <p className='text-white font-bold'><a href='/'>FinTune - Stock Monitoring</a></p>
      </div>
      
      <div class="hidden lg:flex lg:flex-1 lg:justify-end">
      <div class="collapse navbar-collapse" id="navbars">
        <ul className='navbar-nav ml-auto'>
            <li className='nav-item'>
              <a className='nav-link page-scroll' href='#product'>Product</a>
            </li>
            <li className='nav-item'>
              <a className='nav-link page-scroll' href='#pricing'>Pricing</a>
            </li>
          </ul>
      </div>
        
        <div class="flex justify-between lg:justify-start">
          {!isUserAuthenticated() ? (
          <>
            <button
              class="inline-flex items-center justify-between 
              rounded-full bg-indigo-600 px-3 py-2 
              text-sm font-semibold text-white shadow-sm 
              hover:bg-indigo-500 focus-visible:outline 
              focus-visible:outline-2 focus-visible:outline-offset-2 
              focus-visible:outline-indigo-600 lg:ml-6 lg:justify-start"
              role="link"
              onClick={() => navigate("/register")}
            >Sign up</button>
            <button
              class="inline-flex items-center rounded-full
            bg-white px-3 py-2 text-sm font-semibold
              text-gray-900 hover:bg-gray-50 focus-visible:outline 
              focus-visible:outline-2 focus-visible:outline-offset-2
              focus-visible:outline-white ml-2"
              role="link"
              onClick={() => openTab("/signin")}
            >Sign In
            <span aria-hidden="true" class="ml-1">→</span>
            </button>
          </>
          ):(
            <>
              <div className='' style={signoutMenuStyle()}> {`${getUsername()}`}
              <button
                class="inline-flex items-center justify-between 
                rounded-full bg-indigo-600 px-3 py-2 
                text-sm font-semibold text-white shadow-sm 
                hover:bg-indigo-500 focus-visible:outline 
                focus-visible:outline-2 focus-visible:outline-offset-2 
                focus-visible:outline-indigo-600 lg:ml-6 lg:justify-start"
                role="link"
                onClick={logout}
              >
              Logout</button> </div>
            </>
          )}
        </div> 
      
      </div>
     
    </div>
  );
};

export default Navbar;