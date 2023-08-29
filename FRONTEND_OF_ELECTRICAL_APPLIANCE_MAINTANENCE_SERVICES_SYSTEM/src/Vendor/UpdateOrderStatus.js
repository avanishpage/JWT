
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './css/UpdateOrderStatus.css';

function UpdateOrderStatus() {
    const [jobStatusData, setJobStatusData] = useState([]);
    var vendor = JSON.parse(localStorage.getItem('vendor'));
    useEffect(() => {
        fetchJobStatusData();
    }, []);

    const fetchJobStatusData = () => {
        axios.get(`http://localhost:8080/order/vendor/${vendor.id}`)
            .then(response => {
                setJobStatusData(response.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    };

    const updateJobStatus = (id, newStatus) => {
        axios.patch(`http://localhost:8080/order/changeJobStatus/${id}?jobStatus=${newStatus}`)
            .then(response => {
                fetchJobStatusData(); 
            })
            .catch(error => {
                console.error('Error updating job status:', error);
            });
    };

    return (
        <div className="update-order-status">
            <h1>Update Order Status</h1>
            <table className="job-status-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Job Status</th>
                        <th>Timestamp</th>
                        <th>Update Status</th>
                    </tr>
                </thead>
                <tbody>
                    {jobStatusData.map(job => (
                        <tr key={job.id}>
                            <td>{job.id}</td>
                            <td>{job.jobStatus}</td>
                            <td>{job.timeStamp}</td>
                            <td>
                                <form
                                    onSubmit={e => {
                                        e.preventDefault();
                                        const newStatus = e.target.status.value;
                                        updateJobStatus(job.id, newStatus);
                                    }}
                                >
                                    <select name="status" defaultValue={job.jobStatus} className="status-select">
                                        <option value="SCHEDULED">SCHEDULED</option>
                                        <option value="WORKING">WORKING</option>
                                        <option value="COMPLETED">COMPLETED</option>
                                        <option value="CANCELED">CANCELED</option>
                                    </select>
                                    <button type="submit" className="update-button">Update</button>
                                </form>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default UpdateOrderStatus;

