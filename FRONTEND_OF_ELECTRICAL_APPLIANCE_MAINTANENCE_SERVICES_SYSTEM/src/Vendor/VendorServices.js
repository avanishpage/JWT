

import React, { useState, useEffect } from "react";
import axios from "axios";

import { Link } from "react-router-dom";
import './css/VenderServices.css'

function VendorServices() {
  var vendor = JSON.parse(localStorage.getItem('vendor'));
  const [servicesData, setServicesData] = useState([]);
  const id = 0;

  useEffect(() => {
    fetchServicesData();
  }, []);

  const fetchServicesData = () => {
    axios.get(`http://localhost:8080/vendor/services/${vendor.id}`)
      .then(response => {
        setServicesData(response.data);
      })
      .catch(error => {
        console.error('Error fetching services:', error);
      });
  };

  const handleDelete = (serviceId) => {
    axios.delete(`http://localhost:8080/service/${serviceId}/vendor/${vendor.id}/delete/`)
      .then(response => {
        console.log('Service deleted:', response.data);
        fetchServicesData(); 
      })
      .catch(error => {
        console.error('Error deleting service:', error);
      });
  };

  return (
    <div className="vendor-services">
      <h1>Vendor Services</h1>
      <table className="service-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Category</th>
            <th>Update</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {servicesData.map(service => (
            <tr key={service.id}>
              <td>{service.name}</td>
              <td>{service.description}</td>
              <td>${service.price}</td>
              <td>{service.category}</td>
              <td><Link to={`/update/${service.id}`}><button className="update-button">Update</button></Link></td>
              <td><button className="delete-button" onClick={() => handleDelete(service.id)}>Delete</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default VendorServices;
