import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import './NavigationBar.css';

const NavigationBar = () => {

   const navigate = useNavigate();

  const handleLogout = () => {
    // Clear user data from local storage

    localStorage.removeItem('customer');
    //setIsLoggedInCustomer(false);
    navigate("/");
    // Navigate to the desired page (e.g., home page)
    // You can use `useNavigate` if you're using React Router
    // navigate('/customer/login');
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container">
        <NavLink className="navbar-brand" to="/customer/categories">Tech Revive</NavLink>
        <button
          className="navbar-toggler"
          type="button" 
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ml-auto">
            <li className="nav-item">
              <NavLink className="nav-link" to="/my-account">My Account</NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/my-orders">My Orders</NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/cart">Cart</NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/" onClick={handleLogout}>Logout</NavLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default NavigationBar;
