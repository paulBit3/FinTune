

import { useAuth } from '../../../components/context/AuthentContext'

export function Logout () {
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

//get the username of the authenticated user
const getUsername = () => {
  const user = getUser()
  return user ? user.data.username : ''
}


  return {
    logout,
    getUsername,
    signoutMenuStyle
  }
}

