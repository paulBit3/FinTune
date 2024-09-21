import React, { useState } from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';


import Register from './pages/auth/signup/Register';
import Login from './pages/auth/signin/Login';
//import { Logout } from './pages/auth/signout/Logout';

import Home from './components/Home';
import StockList2 from './components/StockList2';
import CompanyList from './components/CompanyList';
import Company from './components/Company';
import BuyStock from './components/BuyStock';
import SellStock from './components/SellStock';

function App() {
  const isAuthenticated = useState(true)

  const ProtectedRoute = ({ isAuthenticated, children }) => {
      if (!isAuthenticated) {
          return <Navigate to="/register" replace />;
      }
  
      return children;
  };


  return (
    <>
    <BrowserRouter>

      <Routes>
        <Route path='/' element={<Home />} />

        <Route index element={<Home />} />
        <Route path='/register' element={ <Register />}/>
        <Route path='/signin' element={ <Login />}/>
        <Route path='*' element={
          <ProtectedRoute isAuthenticated={isAuthenticated}>
            <Routes>
              {/* <Route path='/logout' element={<Logout />} /> */}
              <Route path='/companies' element={<CompanyList />} />
              <Route path='/company' element={<Company />} />
              <Route path='/stocks' element={<StockList2 />} />
              <Route path='/stocks/buyStock/:stock_id' element={<BuyStock />} />
              <Route path='/stocks/sellStock/:stock_id' element={<SellStock />} />
            </Routes>
          </ProtectedRoute>
        }/>
      </Routes>


    </BrowserRouter>
    </>
  );
}

export default App;
