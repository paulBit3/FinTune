//import api from "../auth/../../api/axiosConfig";
import api from "../../api/axiosConfig";


function bearerAuth(user) {
    console.log(user)
    return `Bearer ${user.accessToken}`
}

class AuthService {

    //registration
    Signup(user) {
        console.log(user)
        return api.post(`/signup`, user);
    }


    //login with email
    Signin(email, password) {
        console.log(email, password)
        return api.post('/login1', {email, password})
    }

     //login with username
    Signin2(user) {
        return api.get(`/login2${user}`, {
            headers: {'Authorization': bearerAuth(user)}
        })
    }

    //logout
    signout() {
        localStorage.removeItem('user');
    }

    

} export default new AuthService();