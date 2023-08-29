import React, { useState } from 'react';

const locationData = {
    India: {
      Maharashtra: {
        Pune: ['Pune City ', 'Pimpri Chinchwad', 'Khed Shivapur',],
        Mumbai: ['Panvel', 'Thane'],
        Nashik: ['Malegaon', 'Sinnar'],
        Sangli:['Sangli','Miraj'],
        Kolhapur:['Kolhapur']
      },
      Karnataka: {
        'Bangalore Urban District': ['Bangaluru','Indiranagar'],
      },
      Telangana: {
        'Hyderabad District': ['Hyderabad City'],
      },
    },
  };

function CascadingDropdowns({onSelectLocation}) {
  const [selectedCountry, setSelectedCountry] = useState('');
  const [selectedState, setSelectedState] = useState('');
  const [selectedDistrict, setSelectedDistrict] = useState('');
  const [selectedCity, setSelectedCity] = useState('');

  const availableStates = Object.keys(locationData[selectedCountry] || {});
  const availableDistricts = Object.keys(locationData[selectedCountry]?.[selectedState] || []);
  const availableCities = locationData[selectedCountry]?.[selectedState]?.[selectedDistrict] || [];
  return (
    <div>
      <label htmlFor="country">Country <span>*</span></label>
      <select
        id="country"
        name="country"
        value={selectedCountry}
        onChange={(e) => {
          setSelectedCountry(e.target.value);
          setSelectedState('');
          setSelectedDistrict('');
          setSelectedCity('');
        }}
        required
      >
        <option value="" disabled>Select Country</option>
        <option value="India">India</option>
      </select>

      <label htmlFor="state">State <span>*</span></label>
      <select
        id="state"
        name="state"
        value={selectedState}
        onChange={(e) => {
          setSelectedState(e.target.value);
          setSelectedDistrict('');
          setSelectedCity('');
        }}
        required
      >
        <option value="" disabled>Select State</option>
        {availableStates.map((state) => (
          <option key={state} value={state}>
            {state}
          </option>
        ))}
      </select>

      <label htmlFor="district">District <span>*</span></label>
      <select
        id="district"
        name="district"
        value={selectedDistrict}
        onChange={(e) => {
          setSelectedDistrict(e.target.value);
          setSelectedCity('');
        }}
        required
      >
        <option value="" disabled>Select District</option>
        {availableDistricts.map((district) => (
          <option key={district} value={district}>
            {district}
          </option>
        ))}
      </select>

      <label htmlFor="city">City <span>*</span></label>
      <select
        id="city"
        name="city"
        value={selectedCity}
        onChange={(e) => {
        //   setSelectedState(e.target.value);
        //   setSelectedDistrict('');
          setSelectedCity(e.target.value);
          onSelectLocation({
            city: e.target.value,
            district: selectedDistrict,
            state: selectedState,
            country: selectedCountry,
          });
        }}
        required
      >
        <option value="" disabled>Select City</option>
        {availableCities.map((city) => (
          <option key={city} value={city}>
            {city}
          </option>
        ))}
      </select>
    </div>
  );
}

export default CascadingDropdowns;
