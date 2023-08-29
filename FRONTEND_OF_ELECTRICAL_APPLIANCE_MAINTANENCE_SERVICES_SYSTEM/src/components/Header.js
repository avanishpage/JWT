
import React, { useState } from 'react';
import { NavLink, Link } from 'react-router-dom';
import './css/Header.css';

function Header() {
    const [showLoginDropdown, setShowLoginDropdown] = useState(false);
    const [showRegisterDropdown, setShowRegisterDropdown] = useState(false);

    const toggleLoginDropdown = () => {
        setShowLoginDropdown(!showLoginDropdown);
    };

    const toggleRegisterDropdown = () => {
        setShowRegisterDropdown(!showRegisterDropdown);
    };

    return (
        <header>
            <nav>
                <div className="nav-left">
                    <a href="/" className="company-name">Tech Revive</a>
                </div>
                <div className="nav-right">
                    <ul>
                        <li class="nav-item">
                            <Link to="/">Home</Link>
                        </li>
                        <li class="nav-item">
                            <Link to="/about">About</Link>
                        </li>
                        <li class="nav-item">
                            <Link to="/contact">ContactUs</Link>
                        </li>
                        <li className="nav-item dropdown" onMouseEnter={toggleLoginDropdown} onMouseLeave={toggleLoginDropdown}>
                            <div className="nav-link">Log In</div>
                            {showLoginDropdown && (
                                <ul className="dropdown-menu">
                                    <li><Link to="/signin/customer" className="dropdown-item">Customer</Link></li>
                                    <li><Link to="/signin/vendor" className="dropdown-item">Vendor</Link></li>
                                </ul>
                            )}
                        </li>
                        <li className="nav-item dropdown" onMouseEnter={toggleRegisterDropdown} onMouseLeave={toggleRegisterDropdown}>
                            <div className="nav-link">Register</div>
                            {showRegisterDropdown && (
                                <ul className="dropdown-menu">
                                    <li><Link to="/register/customer" className="dropdown-item">Customer</Link></li>
                                    <li><Link to="/register/vendor" className="dropdown-item">Vendor</Link></li>
                                </ul>
                            )}
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
    );
}

export default Header;




