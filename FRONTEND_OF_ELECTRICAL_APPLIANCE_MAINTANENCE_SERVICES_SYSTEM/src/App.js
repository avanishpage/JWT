import React,{ useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage';
import SignInPage from './components/SignInPage';
import SignInPageVendor from './components/SignInPageVendor';
import RegistrationPage from './components/RegistrationPage';
import RegistrationPageVendor from './components/RegistrationPageVendor';
import ForgotPasswordPage from './components/ForgotPasswordPage';
import AboutUsPage from './components/AboutUsPage';
import ContactUsPage from './components/ContactUsPage';
import AddImage from './components/AddImage';
import Header from './components/Header';
import Categories from './components/Categories';
import Footer from './components/Footer';
import Sidebar from './Vendor/Sidebar';
import GetOrders from './Vendor/GetOrders';
import VenderDetails from './Vendor/VenderDetails';
import UpdateServices from './Vendor/UpdateServices';
import UpdateVenderDetails from './Vendor/UpdateVenderDetails';
import VendorServices from './Vendor/VendorServices';
import UpdateOrderStatus from './Vendor/UpdateOrderStatus';
import AddService from './Vendor/AddService';
import LogoutVendor from './Vendor/LogoutVendor';
import NavigationBar from './components/NavigationBar';
function App() {
  const [isLoggedInVendor, setIsLoggedInVendor] = useState(false);
  const [isLoggedInCustomer, setIsLoggedInCustomer] = useState(false);

  var customer = localStorage.getItem('customer');

  return (
    <div>
      {!customer && <Header></Header>}
      
      <p></p>
      <p></p>      
        <p></p>
        <div></div>
      <Routes>
        <Route path="/" exact element={<HomePage />} />
        <Route path="/about" element={<AboutUsPage />} />
        <Route path="/contact" element={<ContactUsPage />} />
        <Route path="/register/customer" element={<RegistrationPage />} />
        <Route path="/register/vendor" element={<RegistrationPageVendor />} />
        <Route path="/register/vendor/addimage" element={<AddImage />} />
        <Route path="/signin/customer" element={<SignInPage />} />
        <Route path="/signin/vendor" element={<SignInPageVendor setIsLoggedInVendor={setIsLoggedInVendor} />} />
        <Route path="/forgotpassword" element={<ForgotPasswordPage />} />

      </Routes>

      
      {isLoggedInVendor && ( 
          <Sidebar>
            <Routes>
              <Route path="/getorders" element={<GetOrders />} />
              <Route path="/vendor/:id" element={<VenderDetails />} />
              <Route path="/update/:id" element={<UpdateServices />} />
              <Route path="/addService" element={<AddService />} />
              <Route path="/updateOrderStatus" element={<UpdateOrderStatus />} />
              <Route path="/updatevendordetails" element={<UpdateVenderDetails />} />
              <Route path="/serviceDetails" element={<VendorServices />} />
              <Route path="/logoutVendor" element={<LogoutVendor setIsLoggedInVendor={setIsLoggedInVendor}/>} />
            </Routes>
          </Sidebar>
        )}   
        {customer && ( 
            <Routes>
        <Route path="/customer/categories" element={<Categories />} />

        </Routes>
          
        )}
    </div>
  );
}

export default App;
