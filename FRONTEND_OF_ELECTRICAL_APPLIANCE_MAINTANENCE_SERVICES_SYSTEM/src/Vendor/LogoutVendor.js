
import React from "react";
import { Link, useNavigate } from "react-router-dom";
import "./css/LogoutVendor.css"; 

function LogoutVendor({setIsLoggedInVendor}) {
  const navigate = useNavigate();

  function logout() {
    localStorage.removeItem('vendor');
    setIsLoggedInVendor(false);
    navigate("/");
  }

  return (
    <div className="logout-container">
        <div>
      <h1>Are you sure you want to logout</h1>
      </div>
      <div>
      <table id="table">
        <tr>
          <td>
            <button className="logout-button-yes" onClick={logout}>
              YES
            </button>
          </td>
          <td>
            <Link to="/getorders">
              <button className="logout-button-no">NO</button>
            </Link>
          </td>
        </tr>
      </table>
      </div>
    </div>
  );
}

export default LogoutVendor;
