import React, { useState, useEffect } from 'react';
import { Link ,useNavigate} from 'react-router-dom';
import './css/RegistrationPage.css';
import CascadingDropdowns from './CascadingDropdowns';
import Header from './Header';
import Footer from './Footer';
import axios from 'axios';
function RegistrationPage() {
  const navigate=useNavigate();
  const [errors, setErrors] = useState({});
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

  // const handleInputChange = (e) => {
  //   const { name, value } = e.target;
  //   setFormData((prevData) => ({
  //     ...prevData,
  //     [name]: value,
  //   }));
  // };
  const handleInputChange = (e) => {
    const { name, value } = e.target;

    if (name === 'city' || name === 'district' || name === 'state' || name === 'country') {
      setFormData((prevData) => ({
        ...prevData,
        [name]: value,
      }));
    } else {
      setFormData((prevData) => ({
        ...prevData,
        [name]: value,
      }));
    }
  };

  const handleSelectLocation = (location) => {
    setFormData((prevData) => ({
      ...prevData,
      city: location.city,
      district: location.district,
      state: location.state,
      country: location.country,
    }));
  };

  const handleSubmit = async(e) => {
    debugger
    e.preventDefault();
    console.log(formData);
    const validationErrors = {};

    if (!formData.firstName) validationErrors.firstName = 'First name is required';
    if (!formData.lastName) validationErrors.lastName = 'Last name is required';
    if (!formData.email) validationErrors.email = 'Email is required';
    if (!formData.password) validationErrors.password = 'Password is required';
    // if (!formData.address) validationErrors.address = 'Address is required';
    if (!formData.city) validationErrors.city = 'City is required';
    if (!formData.phoneNumber) validationErrors.phoneNumber = 'Phone number is required'; 

    if (!/^\d{6}$/.test(formData.pincode)) {
      validationErrors.pincode='Pincode must be 6 digits.';
      return;
    }

    if (!/^\d{10}$/.test(formData.phoneNumber)) {
      validationErrors.phoneNumber='Phone number must be 10 digits.';
      return;
    }
    if (formData.password !== formData.confirmPassword) {
      validationErrors.password='Password and Confirm Password do not match.';
      return;
    }
    // const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{4,12})$/;
    // if (!passwordRegex.test(formData.password)) {
    //   validationErrors.password="Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 special character, 1 numeral and length between 4-12 characters.";
    //   return;
    // }

    setErrors(validationErrors);

    console.log('si')
    if (Object.keys(validationErrors).length === 0) {
      // Submit the form or call an API here
    
      console.log('siuu')
      const user = {
        firstName: formData.firstName,
        lastName: formData.lastName,
        email: formData.email,
        password: formData.password,
        flatNo: formData.flatNo,
        area:formData.area,
        city: formData.city,
        district: formData.district,
        state: formData.state,
        country: formData.country,
        pincode: formData.pincode,
        phoneNumber: formData.phoneNumber,
      };
      try {
        // Make POST request using Axios
        await axios.post('http://localhost:8080/customer/add', user);

        // Navigate to the home page after successful registration
        navigate('/');
      } catch (error) {
        console.error('Error registering user:', error);
      }
      
    }
  };

  return (
    
    
    <div className="registration-form">
      <h2>Register Customer</h2>
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
        <label htmlFor="flatNo">Flat No./House No. <span> *</span></label>
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

            {/* <CascadingDropdowns></CascadingDropdowns> */}
            <CascadingDropdowns onSelectLocation={handleSelectLocation} />

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

        <button type="submit">Register</button>
        <p>
          Already registered? <Link to="/signin/customer">Login here</Link>
        </p>
      </form>

    </div>
    
  );
}

export default RegistrationPage;
