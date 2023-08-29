

import React, { useState, useEffect } from 'react';
import axios from 'axios';

const DeleteService = ({ serviceId }) => {
  var vendor = JSON.parse(localStorage.getItem('vendor'))
  const [isDeleted, setIsDeleted] = useState(false);

  const handleDelete = () => {
    // Send delete request to Spring Boot backend
    axios.delete(`http://localhost:8080/vendor/update/${vendor.id}/service/${serviceId}`)
      .then(response => {
        console.log('Service deleted:', response.data);
        setIsDeleted(true);
      })
      .catch(error => {
        console.error('Error deleting service:', error);
      });
  };

  if (isDeleted) {
    return <div>Service has been deleted.</div>;
  }

  return (
    <div>
      <h1>Delete Service</h1>
      <p>Are you sure you want to delete this service?</p>
      <button onClick={handleDelete}>Delete</button>
    </div>
  );
};

export default DeleteService;