import React, { useEffect, useState } from 'react';
import { NavLink ,useNavigate} from 'react-router-dom';

import axios from 'axios';

const LoginUser = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });

  const navigate = useNavigate();

  const [loginStatus, setLoginStatus] = useState(null);
  const [customer, setCustomer] = useState(null);
  const [loginError, setLoginError] = useState(false);


  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    

  
    // try {
      const response = await axios.post('http://localhost:8080/customer/login', {
        email: formData.email,
        password: formData.password,
      }).then(response =>{
        localStorage.setItem('customer',JSON.stringify(response.data))
        setLoginStatus('Login successful');
        setLoginError(false);

        navigate('/customer/categories');
        console.log(response.data);
        console.log(response.status);
        console.log(response.statusText);

      }).catch(e => {
        setLoginStatus('Wrong credentials!!!!!!siuuuu');
        setLoginError(true);
        console.log('here');
      })
  
   
  };

  return (
    
    <div className="login-user container">
      <h2>Login User</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Email</label>
          <input
            type="email"
            className="form-control"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Password</label>
          <input
            type="password"
            className="form-control"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">Login</button>
      </form>
      {loginStatus && <p className={`login-status ${loginStatus === 'Login successful' ? 'success' : 'error'}`}>{loginStatus}</p>}
      {loginError && <p className="login-error">Invalid email or password. Please try again.</p>}
      {loginStatus === 'Login successful' && customer && (
        <div className="user-details">
      <h3>Welcome, {customer.id}!</h3>
          <p>Name: {customer.firstName} {customer.lastName}</p>
          <p>City: {customer.city}</p>
          </div>
      )}
    </div>
  );
};

export default LoginUser;
