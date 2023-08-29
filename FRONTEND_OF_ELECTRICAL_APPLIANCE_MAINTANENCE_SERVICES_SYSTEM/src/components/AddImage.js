import React, { useState } from "react";
import "./css/AddImage.css"; 
import { useHistory } from 'react-router-dom';
function AddImage() {
  const [selectedImage, setSelectedImage] = useState(null);
  const [registrationSuccess, setRegistrationSuccess] = useState(false);

  const handleImageChange = (event) => {
    const file = event.target.files[0];
    if (file && (file.type === "image/jpeg" || file.type === "image/png")) {
      setSelectedImage(file);
    } else {
      setSelectedImage(null);
      alert("Please select a valid image file (jpg or png).");
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (selectedImage) {
     
      setRegistrationSuccess(true);
    } else {
      alert("Please select an image before submitting.");
    }
  };

  if (registrationSuccess) {
    return (
      <div>
        <h2>Registration Successful!</h2>
        <AddImage />
      </div>
    );
  }

  return (
    <div className="add-image-container">
      <h5>Add Your Image<span> *</span></h5>
      <form onSubmit={handleSubmit} className="add-image-form">
        <input type="file" accept=".jpg,.jpeg,.png" onChange={handleImageChange} required/>
        <button type="submit">Submit</button>
      </form>
      {selectedImage && (
        <div className="selected-image">
          <h3>Selected Image:</h3>
          <img src={URL.createObjectURL(selectedImage)} alt="Selected" />
        </div>
      )}
    </div>
  );
}

export default AddImage;
