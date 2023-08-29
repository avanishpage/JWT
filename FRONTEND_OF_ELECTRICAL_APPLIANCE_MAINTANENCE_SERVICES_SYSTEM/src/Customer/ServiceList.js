import React, { useState, useEffect } from 'react';
import axios from 'axios'; // Import Axios library
import { useParams } from 'react-router-dom';
import NavigationBar from './NavigationBar'; // Import your NavigationBar component

import './ServiceList.css'

const ServicesList = () => {
  const { category } = useParams();
  const [services, setServices] = useState([]);

  useEffect(() => {
    const fetchServices = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/service/category`, {
          params: {
            category: category
          }
        });
        setServices(response.data);
      } catch (error) {
        console.error('Error fetching services:', error);
      }
    };

    fetchServices();
  }, [category]);

  return (
    <><NavigationBar /><div className="services-list">
      <h2>Services for {category}</h2>
      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          {services.map(service => (
            <tr key={service.id}>
              <td>{service.id}</td>
              <td>{service.name}</td>
              <td>{service.description}</td>
              <td>{service.price}</td>

            </tr>
          ))}
        </tbody>
      </table>
    </div></>
  );
};

export default ServicesList;
