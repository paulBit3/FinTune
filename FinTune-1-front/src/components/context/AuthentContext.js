import { useState, useEffect } from "react";


export function useAuth() {
   const [user, setUser] = useState(null);
   const [isLoggedIn, setIsLoggedIn]=useState(false)


    useEffect(() =>{
        const storedUser = JSON.parse(localStorage.getItem('user'))
      
        setUser(storedUser) 
    }, [])

  

    const getUser = () => {
        return JSON.parse(localStorage.getItem('user'))
    }



    const login = user => {
        localStorage.setItem('user', JSON.stringify(user))
        setIsLoggedIn(true)
        setUser(user)
    }

    const signout = () => {
        localStorage.removeItem('user')
        setIsLoggedIn(false)
        setUser(null)
    }
    
    return {
        user, 
        getUser, 
        login,
        signout,
    }
}