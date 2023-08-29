
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './css/GetOrders.css';

function GetOrders() {
    var vendor = JSON.parse(localStorage.getItem('vendor'));
    const [jobStatusData, setJobStatusData] = useState([]);

    useEffect(() => {
        // Fetch data from backend API
        axios.get(`http://localhost:8080/order/vendor/${vendor.id}`)
            .then(response => {
                setJobStatusData(response.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);

    return (
        <div className="get-orders">
            <h1>Order Status Page</h1>
            <table className="order-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Job Status</th>
                        <th>Timestamp</th>
                    </tr>
                </thead>
                <tbody>
                    {jobStatusData.map(job => (
                        <tr key={job.id}>
                            <td>{job.id}</td>
                            <td>{job.jobStatus}</td>
                            <td>{job.timeStamp}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default GetOrders;
