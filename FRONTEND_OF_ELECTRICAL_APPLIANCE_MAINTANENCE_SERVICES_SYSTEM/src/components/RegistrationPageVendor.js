import React, { useState, useEffect } from 'react';
import { Link,useNavigate } from "react-router-dom";
import { useHistory } from 'react-router-dom';
import './css/RegistrationPage.css';
import CascadingDropdowns from './CascadingDropdowns';
import AddImage from './AddImage';
import Header from './Header';
import Footer from './Footer';
function RegistrationPageVendor() {
  // const history = useHistory();
  const navigate=useNavigate();
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    flatNo: '',
    area: '',
    city: '',
    district: '',
    state: '',
    country: '',
    pincode: '',
    phoneNumber: '',
    password: '',
    confirmPassword: '',
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formData);

    if (!/^\d{6}$/.test(formData.pincode)) {
      alert("Pincode must be 6 digits.");
      return;
    }

    if (!/^\d{10}$/.test(formData.phoneNumber)) {
      alert("Phone number must be 10 digits.");
      return;
    }
    if (formData.password !== formData.confirmPassword) {
      alert("Password and Confirm Password do not match.");
      return;
    }
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{4,12})$/;
    if (!passwordRegex.test(formData.password)) {
      alert("Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 special character,1 numeral and length between 4-12 characters.");
      return;
    }
    navigate("/register/vendor/addimage")

  };

  return (
    
    <div className="registration-form">
      <h2>Register Vendor</h2>
      <form onSubmit={handleSubmit}>

        <label htmlFor="firstName">First Name <span> *</span></label>
        <input
          type="text"
          id="firstName"
          name="firstName"
          value={formData.firstName}
          onChange={handleInputChange}
          placeholder="Enter your first name"
          required
        />

        <label htmlFor="lastName">Last Name <span> *</span></label>
        <input
          type="text"
          id="lastName"
          name="lastName"
          value={formData.lastName}
          onChange={handleInputChange}
          placeholder="Enter your last name"
          required
        />



        <label htmlFor="email">Email <span> *</span></label>
        <input
          type="email"
          id="email"
          name="email"
          value={formData.email}
          onChange={handleInputChange}
          required
          placeholder="Enter your email"
        />
        <label htmlFor="password">Password <span>*</span></label>
        <input
          type="password"
          id="password"
          name="password"
          value={formData.password}
          onChange={handleInputChange}
          placeholder="Enter your password"
          required
        />


        <label htmlFor="confirmPassword">Confirm Password <span>*</span></label>
        <input
          type="password"
          id="confirmPassword"
          name="confirmPassword"
          value={formData.confirmPassword}
          onChange={handleInputChange}
          placeholder="Confirm your password"
          required
        />
        <label htmlFor="adderss">Adderss:</label>
        <label htmlFor="flatNo">Shop No. <span> *</span></label>
        <input
          type="text"
          id="flatNo"
          name="flatNo"
          value={formData.flatNo}
          onChange={handleInputChange}
          placeholder="Enter your FlatNo/HouseNo"
          required
        />

        <label htmlFor="area">Area <span> *</span></label>
        <input
          type="text"
          id="area"
          name="area"
          value={formData.area}
          onChange={handleInputChange}
          placeholder="Enter your Area"
          required
        />

        <CascadingDropdowns></CascadingDropdowns>

        <label htmlFor="pincode">Pincode <span> *</span></label>
        <input
          type="text"
          id="pincode"
          name="pincode"
          value={formData.pincode}
          onChange={handleInputChange}
          placeholder="Enter your pincode"
          required
        />

        <label htmlFor="phoneNumber">Phone Number <span> *</span></label>
        <input
          type="tel"
          id="phoneNumber"
          name="phoneNumber"
          value={formData.phoneNumber}
          onChange={handleInputChange}
          placeholder="Enter your phone no."
          required
        />


        <button type="submit">Continue</button>
        <p>
          Already registered? <Link to="/signin/vendor">Login here</Link>
        </p>
      </form>

    </div>
    
  );
}

export default RegistrationPageVendor;
