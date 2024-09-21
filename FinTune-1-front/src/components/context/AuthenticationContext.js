import { useState} from "react";



class AuthenticationContext {
    
    storedUser() {
        const [user, setUser] = useState(null);
        const storedUser= JSON.parse(localStorage.getItem('user'));
        setUser(storedUser);
    }
    

    getUser() {
        return JSON.parse(localStorage.getItem('user'));
    }

    userIsAuthenticated () {
        const storedUser = localStorage.getItem('user');
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

    signin (user) {
        const [setUser] = useState(null);
        localStorage.setItem('user', JSON.stringify(user));
        setUser(user);
    }

    signout () {
        const [user, setUser] = useState(null);
        localStorage.removeItem('user');
        setUser(null);
    }
} export default new AuthenticationContext()