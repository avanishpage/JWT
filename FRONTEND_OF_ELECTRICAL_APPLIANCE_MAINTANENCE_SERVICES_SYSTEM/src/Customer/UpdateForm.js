import React, { useState } from 'react';
import axios from 'axios';

const UpdateForm = ({ userData, onUpdateSuccess }) => {
  const [updatedData, setUpdatedData] = useState({
    firstName: userData.firstName,
    lastName: userData.lastName,
    email: userData.email,
    password:userData.password,
    address: userData.address,
    city: userData.city,
    phoneNumber: userData.phoneNumber,
  });


  var customer = JSON.parse(localStorage.getItem('customer'))
  var url = `http://localhost:8080/customer/update/${customer.id}`;
  //var id = customer.id;

  const handleFieldChange = (e) => {
    const { name, value } = e.target;
    setUpdatedData({
      ...updatedData,
      [name]: value,
    });
  };

  const handleUpdateSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.put(url, updatedData);

      if (response.status === 200) {
        // Trigger the onUpdateSuccess callback to update user data in parent component
        onUpdateSuccess(updatedData);
      } else {
        console.error('Update failed:', response.status);
      }
    } catch (error) {
      console.error('Error updating data:', error);
      console.log(customer.id);
    }
  };

  return (
    <div className="update-form">
      <h3>Update Information</h3>
      <form onSubmit={handleUpdateSubmit}>
        <div className="form-group">
          <label htmlFor="firstName">First Name</label>
          <input
            type="text"
            id="firstName"
            name="firstName"
            value={updatedData.firstName}
            onChange={handleFieldChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="lastName">Last Name</label>
          <input
            type="text"
            id="lastName"
            name="lastName"
            value={updatedData.lastName}
            onChange={handleFieldChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            name="email"
            value={updatedData.email}
            onChange={handleFieldChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            name="password"
            value={updatedData.password}
            onChange={handleFieldChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="address">Address</label>
          <input
            type="text"
            id="address"
            name="address"
            value={updatedData.address}
            onChange={handleFieldChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="city">City</label>
          <input
            type="text"
            id="city"
            name="city"
            value={updatedData.city}
            onChange={handleFieldChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="phoneNumber">Phone Number</label>
          <input
            type="tel"
            id="phoneNumber"
            name="phoneNumber"
            value={updatedData.phoneNumber}
            onChange={handleFieldChange}
            required
          />
        </div>
        <div className="form-buttons">
          <button type="submit" className="btn btn-primary">Save</button>
          {/* <button
            type="button"
            className="btn btn-secondary"
            onClick={() => setEditMode(false)}
          >
            Cancel
          </button> */}
        </div>
      </form>
    </div>
  );
};

export default UpdateForm;
