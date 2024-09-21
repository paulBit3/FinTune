import axios from "axios";
import { parseJwt } from "../utils/ApiErrorHelpers";
import { config } from "../utils/ApiHelpers";

//we separate our api base url from our code, and we call in our serives classes baseURL : "http://localhost:8080/api"

//const BASE_URL = 'http://localhost:8080/api';

export default axios.create({
  BASE_URL: config.url.BASE_URL
});

axios.interceptors.request.use(function (config) {
  //if expired token, redirect user to login
  if (config.headers.Authorization) {
    const token = config.headers.Authorization.split(' ')[1]
    const data = parseJwt(token)
    console.log(data)
    //if token expired, logout user
    if (Date.now() > data.exp * 1000) {
      window.location.href = "/signin"
    }
  }
  return config
}, function (error) {
  return Promise.reject(error)
})


