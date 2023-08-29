import React, { useState, useEffect } from 'react';
import NavigationBar from './NavigationBar'; // Import your NavigationBar component
import UpdateForm from './UpdateForm';

const MyAccount = () => {
  const [userData, setUserData] = useState(null);
  const [editMode, setEditMode] = useState(false);

  // Load user data from local storage on component mount
  useEffect(() => {
    const storedUserData = JSON.parse(localStorage.getItem('customer'));
    if (storedUserData) {
      setUserData(storedUserData);
    }
  }, []);

  //set edit toggle to true
  const handleUpdate = () => {
    setEditMode(true);
  };

  const handleUpdateSuccess = (updatedUserData) => {
    // Update user data in local storage
    localStorage.setItem('customer', JSON.stringify(updatedUserData));
    // Exit edit mode
    setEditMode(false);
    // Update the user data in the state
    setUserData(updatedUserData);
  };

  const handleDelete = () => {
    // Clear user data from local storage //axios
    localStorage.removeItem('userData');
    // Navigate to the home page or login page
  };

  if (!userData) {
    return <div>Loading...</div>;
  }

  return (
    <div className="my-account-page">
      <NavigationBar />
      <div className="container">
        <h2>My Account</h2>
        <div className="user-info">
          <p><strong>First Name:</strong> {userData.firstName}</p>
          <p><strong>Last Name:</strong> {userData.lastName}</p>
          <p><strong>Email:</strong> {userData.email}</p>
          <p><strong>Address:</strong> {userData.address}</p>
          <p><strong>City:</strong> {userData.city}</p>
          <p><strong>Phone Number:</strong> {userData.phoneNumber}</p>
        </div>
        <div className="buttons">
          <button className="btn btn-primary" onClick={handleUpdate}>Update</button>
          <button className="btn btn-danger" onClick={handleDelete}>Delete</button>
        </div>
        {editMode && (
          <UpdateForm userData={userData} onUpdateSuccess={handleUpdateSuccess} />
        )}
      </div>
    </div>
  );
};

export default MyAccount;
