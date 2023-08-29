import React, { useState, useEffect } from 'react';
import { IconButton, Menu, MenuItem } from '@mui/material';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import { useLocation } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

function TravellerPage() {

    const navigate = useNavigate();

  const location = useLocation();
  const { logid, name } = location.state;


  const [propertyList, setPropertyList] = useState([]);
  const [searchCity, setSearchCity] = useState('');
  const [filteredPropertyList, setFilteredPropertyList] = useState([]);
  const [anchorEl, setAnchorEl] = useState(null);

  const fetchPropertyListing = async () => {
    try {
      const response = await fetch('http://localhost:8080/getallprops');
      if (response.ok) {
        const data = await response.json();
        setPropertyList(data);
        setFilteredPropertyList(data);
      } else {
        console.log('Failed to fetch property listing');
      }
    } catch (error) {
      console.error('Error fetching property listing:', error);
    }
  };

  useEffect(() => {
    fetchPropertyListing();
  }, []);

  const handleSearch = () => {
    if (searchCity.trim() === '') {
      setFilteredPropertyList(propertyList);
    } else {
      const filteredList = propertyList.filter(property => property.city.toLowerCase() === searchCity.toLowerCase());
      setFilteredPropertyList(filteredList);
    }
  };

  const handleProfileIconClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleProfileMenuClose = () => {
     
    setAnchorEl(null);
  };
  const handleLogout = () => {
    navigate("/");
  }

  return (

    <div>
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <div className="container">
          <a className="navbar-brand" href="#">
            <img src="./airbnb-logo.png" alt="Airbnb" />
          </a>
          <div className="input-group">
            <input
              type="text"
              className="form-control rounded-pill"
              placeholder="Where are you going?"
              value={searchCity}
              onChange={(e) => setSearchCity(e.target.value)}
            />
            <div className="input-group-append">
              <button className="btn btn-primary rounded-pill" type="button" onClick={handleSearch}>
                Search
              </button>
            </div>
          </div>
          <IconButton
            aria-controls="profile-menu"
            aria-haspopup="true"
            onClick={handleProfileIconClick}
          >
            <AccountCircleIcon />

          </IconButton>
          <Menu
            id="profile-menu"
            anchorEl={anchorEl}
            keepMounted
            open={Boolean(anchorEl)}
            onClose={handleProfileMenuClose}
          >
            <MenuItem onClick={handleProfileMenuClose}>{name}</MenuItem>
            <MenuItem onClick={handleProfileMenuClose}>My Account</MenuItem>
            <MenuItem onClick={handleLogout}>Logout</MenuItem>
          </Menu>

        </div>

      </nav>

      <div className="container mt-4">
        <div className="row">
          {filteredPropertyList.map(property => (
            <div className="col-md-4 mb-4" key={property.id_property}>
              <div className="card">
                <img src="https://images.unsplash.com/photo-1568605114967-8130f3a36994?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80" className="card-img-top" alt={property.address} />
                <div className="card-body">
                  <h5 className="card-title">{property.address}</h5>
                  <p className="card-text">State: {property.state}</p>
                  <p className="card-text">City: {property.city}</p>
                  <p className="card-text">Price: {property.price}</p>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default TravellerPage;
