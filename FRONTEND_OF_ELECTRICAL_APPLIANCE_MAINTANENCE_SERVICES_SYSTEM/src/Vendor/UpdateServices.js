
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const UpdateServices = () => {
  var vendor = JSON.parse(localStorage.getItem('vendor'));
  const [item, setItem] = useState({
    id: 0,
    name: '',
    description: '',
    price: 0,
    category: ''
  });

  useEffect(() => {
  
    axios.get(`http://localhost:8080/vendor/services/1`)
      .then(response => {
        setItem(response.data);
      })
      .catch(error => {
        console.error('Error fetching item data:', error);
      });
  }, []);
  
  const handleChange = (e) => {
    const { name, value } = e.target;
    setItem(prevItem => ({
      ...prevItem,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.put(`http://localhost:8080/vendor/update/1/service/6`, item)
      .then(response => {
        console.log('Item data updated:', response.data);
      })
      .catch(error => {
        console.error('Error updating item data:', error);
      });
  };

  if (!item.id) {
    return <div>Loading...</div>;
  }

  const categories = [
    "AC",
    "MICROWAVE",
    "REFRIGERATOR",
    "LAPTOP",
    "PC",
    "WASHING_MACHINE",
    "WATER_FILTER"
  ];

  return (
    <div>
      <h1>Update Item Details</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Name:
          <input type="text" name="name" value={item.name} onChange={handleChange} />
        </label>
        <label>
          Description:
          <input type="text" name="description" value={item.description} onChange={handleChange} />
        </label>
        <label>
          Price:
          <input type="number" name="price" value={item.price} onChange={handleChange} />
        </label>
        <label>
          Category:
          <select name="category" value={item.category} onChange={handleChange}>
            {categories.map(category => (
              <option key={category} value={category}>
                {category}
              </option>
            ))}
          </select>
        </label>
        <button type="submit">Update</button>
      </form>
    </div>
  );
};

export default UpdateServices;
