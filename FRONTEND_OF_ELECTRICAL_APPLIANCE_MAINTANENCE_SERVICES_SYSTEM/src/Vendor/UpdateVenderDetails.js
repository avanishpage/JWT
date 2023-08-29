
import './css/UpdateVenderDetails.css'
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
const UpdateVenderDetails = () => {
  var vendor = JSON.parse(localStorage.getItem('vendor'));
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const [updatedUser, setUpdatedUser] = useState({
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    address: {
      flatNo: '',
      area: '',
      city: '',
      district: '',
      state: '',
      country: '',
      pincode: ''
    },
    phoneNumber: ''
  });

  useEffect(() => {
    axios.get(`http://localhost:8080/vendor/${vendor.id}`)
      .then(response => {
        setUser(response.data);
        setUpdatedUser(response.data); // Set initial form values
      })
      .catch(error => {
        console.error('Error fetching user data:', error);
      });
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUpdatedUser(prevUser => ({
      ...prevUser,
      [name]: value
    }));
  };

  const handleAddressChange = (e) => {
    const { name, value } = e.target;
    setUpdatedUser(prevUser => ({
      ...prevUser,
      address: {
        ...prevUser.address,
        [name]: value
      }
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Send updated user data to Spring Boot backend
    axios.put(`http://localhost:8080/vendor/update/${vendor.id}`, updatedUser)
      .then(response => {
        console.log('User data updated:', response.data);
        navigate('/vendor/:id')
      })
      .catch(error => {
        console.error('Error updating user data:', error);
      });
  };

  if (!user) {
    return <div>Loading...</div>;
  }

  return (
    <div className="update-user">
      <h1>Update User Details</h1>
      <form className="update-form" onSubmit={handleSubmit}>
        <label>
          First Name:
          <input type="text" name="firstName" value={updatedUser.firstName} onChange={handleChange} />
        </label>
        <label>
          Last Name:
          <input type="text" name="lastName" value={updatedUser.lastName} onChange={handleChange} />
        </label>
        <label>
          Email:
          <input type="email" name="email" value={updatedUser.email} onChange={handleChange} />
        </label>
        <label>
          Password:
          <input type="password" name="password" value={updatedUser.password} onChange={handleChange} />
        </label>
        <h2>Address</h2>
        <label>
          Flat No:
          <input type="text" name="flatNo" value={updatedUser.address.flatNo} onChange={handleAddressChange} />
        </label>
        <label>
          Area:
          <input type="text" name="area" value={updatedUser.address.area} onChange={handleAddressChange} />
        </label>
        <label>
          City:
          <input type="text" name="city" value={updatedUser.address.city} onChange={handleAddressChange} />
        </label>
        <label>
          District:
          <input type="text" name="district" value={updatedUser.address.district} onChange={handleAddressChange} />
        </label>
        <label>
          State:
          <input type="text" name="state" value={updatedUser.address.state} onChange={handleAddressChange} />
        </label>
        <label>
          Country:
          <input type="text" name="country" value={updatedUser.address.country} onChange={handleAddressChange} />
        </label>
        <label>
          Pincode:
          <input type="text" name="pincode" value={updatedUser.address.pincode} onChange={handleAddressChange} />
        </label>
        <label>
          Phone Number:
          <input type="text" name="phoneNumber" value={updatedUser.phoneNumber} onChange={handleChange} />
        </label>
        <button type="submit">Update</button>
      </form>
    </div>
  );
};

export default UpdateVenderDetails;
