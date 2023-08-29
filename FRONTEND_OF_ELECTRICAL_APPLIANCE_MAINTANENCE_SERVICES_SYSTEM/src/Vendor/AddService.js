import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './css/AddServices.css'

function AddService() {
    var vendor = JSON.parse(localStorage.getItem('vendor'));
    const [jobStatusData, setJobStatusData] = useState([]);
    const [formData, setFormData] = useState({
        name: '',
        description: '',
        price: 0,
        category: 'AC'
    });

    useEffect(() => {
        fetchJobStatusData();
    }, []);

    const fetchJobStatusData = () => {
        axios.get('YOUR_BACKEND_API_URL')
            .then(response => {
                setJobStatusData(response.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    };

    const handleInputChange = event => {
        const { name, value } = event.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const addNewJobStatus = event => {
        event.preventDefault();

        axios.post(`http://localhost:8080/service/add/${vendor.id}`, formData)
            .then(response => {
                fetchJobStatusData(); 
                setFormData({
                    name: '',
                    description: '',
                    price: 0,
                    category: 'AC'
                });
            })
            .catch(error => {
                console.error('Error adding new job status:', error);
            });
    };

    const categories = [
        "AC",
        "MICROWAVE",
        "REFRIGERATOR",
        "LAPTOP",
        "PC",
        "WASHING_MACHINE",
        "WATER_FILTER"
    ];
    function confirm(){
        alert("Service added successfully")
    }
    return (
        <div className="add-service-container">
            <h1 className="add-service-title">Add Services</h1>
            <form onSubmit={addNewJobStatus} className="add-service-form">
                <table className="add-service-table">
                    <tbody>
                        <tr>
                            <td className="label-cell">Name:</td>
                            <td className="input-cell">
                                <label>
                                    <input
                                        type="text"
                                        name="name"
                                        value={formData.name}
                                        onChange={handleInputChange}
                                        className="input-field"
                                    />
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td className="label-cell">Description:</td>
                            <td className="input-cell">
                                <label>
                                    <input
                                        type="text"
                                        name="description"
                                        value={formData.description}
                                        onChange={handleInputChange}
                                        className="input-field"
                                    />
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td className="label-cell">Price:</td>
                            <td className="input-cell">
                                <label>
                                    <input
                                        type="number"
                                        name="price"
                                        value={formData.price}
                                        onChange={handleInputChange}
                                        className="input-field"
                                    />
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td className="label-cell">Category:</td>
                            <td className="input-cell">
                                <label>
                                    <select
                                        name="category"
                                        value={formData.category}
                                        onChange={handleInputChange}
                                        className="input-field"
                                    >
                                        {categories.map(category => (
                                            <option key={category} value={category}>
                                                {category}
                                            </option>
                                        ))}
                                    </select>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>

                            </td>
                            <td> <button type="submit" className="add-service-button" onClick={confirm}>
                                Add Service
                            </button></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    );
}

export default AddService;
