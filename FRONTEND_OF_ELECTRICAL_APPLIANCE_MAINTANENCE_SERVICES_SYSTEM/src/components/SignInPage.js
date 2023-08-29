import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios'; 
import { NavLink, useNavigate } from 'react-router-dom';
import './css/SignInPage.css';

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

    const response = await axios.post('http://localhost:8080/customer/login', {
      email: formData.email,
      password: formData.password,
    }).then(response => {
      localStorage.setItem('customer', JSON.stringify(response.data))
      setLoginStatus('Login successful');
      setLoginError(false);

      navigate('/customer/categories');
      console.log(response.data);
      console.log(response.status);
      console.log(response.statusText);

    }).catch(e => {
      setLoginStatus('Wrong credentials!!');
      setLoginError(true);
      console.log('here');
    })


  };

  return (
    <div className="sign-in-page">
      <div className="sign-in-container">
        <h2>Sign In Customer</h2>
        <form onSubmit={handleSubmit}>

          <label>Email<span> *</span></label>
          <input
            type="email"
            placeholder="Enter your email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />


          <label >Password<span> *</span></label>
          <input
            type="password"
            placeholder="Enter your password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />

          <div>
            <button type="submit">Sign In</button>
            <Link to="/forgotpassword">Forgot Password?</Link>
          </div>
          <p>
            Don't have an account? <Link to="/register/customer">Register here</Link>
          </p>
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
    </div>

  );
};

export default LoginUser;
