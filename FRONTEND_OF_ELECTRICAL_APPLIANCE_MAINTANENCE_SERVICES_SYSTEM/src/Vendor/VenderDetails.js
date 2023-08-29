
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './css/VenderDetails.css'
import UpdateVenderDetails from './UpdateVenderDetails';
import { Link, useNavigate } from 'react-router-dom';
const VenderDetails = () => {
  var vendor = JSON.parse(localStorage.getItem('vendor'));
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
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
      pincode: '',
    },
    phoneNumber: '',
  });
  
  useEffect(() => {

    axios.get(`http://localhost:8080/vendor/${vendor.id}`)
      .then(response => {
        setFormData(response.data);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  }, []);


  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formData);
  };
  const handleDelete = () => {
  
    const confirmed = window.confirm("Are you sure you want to delete this vendor?");
    if (confirmed) {
      axios.delete(`http://localhost:8080/vendor/delete/${vendor.id}`)
        .then(response => {
          console.log('Vendor deleted:');
        })
        .catch(error => {
          console.error('Error deleting vendor:', error);
        });
    } else {

      console.log('Deletion canceled.');
      navigate('/vendor/:id');
    }
  };
  return (
    <div className="user-details">
      <h1>Vendor Details</h1>
      <table className="details-table">
        <tbody>
          <tr>
            <td className="field-name">First Name:</td>
            <td>{formData.firstName}</td>
          </tr>
          <tr>
            <td className="field-name">Last Name:</td>
            <td>{formData.lastName}</td>
          </tr>
          <tr>
            <td className="field-name">Email:</td>
            <td>{formData.email}</td>
          </tr>
          <tr>
            <td className="field-name">Password:</td>
            <td>{formData.password}</td>
          </tr>
        </tbody>
      </table>

      <h2>Address</h2>
      <table className="details-table">
        <tbody>
          <tr>
            <td className="field-name">Flat No:</td>
            <td>{formData.address.flatNo}</td>
          </tr>
          <tr>
            <td className="field-name">Area:</td>
            <td>{formData.address.area}</td>
          </tr>
          <tr>
            <td className="field-name">City:</td>
            <td>{formData.address.city}</td>
          </tr>
          <tr>
            <td className="field-name">District:</td>
            <td>{formData.address.district}</td>
          </tr>
          <tr>
            <td className="field-name">State:</td>
            <td>{formData.address.state}</td>
          </tr>
          <tr>
            <td className="field-name">Country:</td>
            <td>{formData.address.country}</td>
          </tr>
          <tr>
            <td className="field-name">Pincode:</td>
            <td>{formData.address.pincode}</td>
          </tr>
        </tbody>
      </table>

      <table className="details-table">
        <tbody>
          <tr>
            <td className="field-name">Phone Number:</td>
            <td>{formData.phoneNumber}</td>
          </tr>
        </tbody>
      </table>

      <Link to="/updatevendordetails"><button className="action-button">Update Details</button></Link>
      <button id="delete-button" onClick={handleDelete}>Delete</button>
    </div>
  );
};

export default VenderDetails;