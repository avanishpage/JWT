
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './css/SignInPage.css'; 
import axios from 'axios';

function SignInPageVendor({ setIsLoggedInVendor }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Basic email validation
    if (!email || !email.includes('@')) {
      setErrorMessage('Please enter a valid email');
      return;
    }

    // Basic password validation
    if (!password) {
      setErrorMessage('Please enter your password');
      return;
    }

    // Send a request to the backend to check if the user exists
    axios.post('http://localhost:8080/vendor/login', {
      email: email,
      password: password,
    })
    .then(response => {
      if (!response.data.exists) {
        // User exists, navigate to UserDetailsPage
        localStorage.setItem('vendor', JSON.stringify(response.data));
        setIsLoggedInVendor(true);
        navigate('/getorders');
      } else {
        // User doesn't exist, display an error or handle accordingly
        setErrorMessage('Invalid email or password');
      }
    })
    .catch(error => {
      console.error('Error checking user:', error);
      setErrorMessage('Invalid email or password');
    });
  };

  return (
    <div className="sign-in-page">
      <div className="sign-in-container">
        <h2>Sign In Vendor</h2>
        <form onSubmit={handleSubmit}>
          <label htmlFor="email">Email<span> *</span></label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={handleEmailChange}
            placeholder="Enter your email"
            required
          />

          <label htmlFor="password">Password<span> *</span></label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={handlePasswordChange}
            placeholder="Enter your password"
            required
          />

          {errorMessage && <p className="error-message">{errorMessage}</p>}

          <div>
            <button type="submit">Sign In</button>
            <Link to="/ForgotPasswordPage">Forgot Password?</Link>
          </div>
          <p>
            Don't have an account? <Link to="/register/vendor">Register here</Link>
          </p>
        </form>
      </div>
    </div>
  );
}

export default SignInPageVendor;
