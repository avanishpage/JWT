import React, { useState } from 'react';
import {
    FaTh,
    FaBars,
    FaUserAlt,
    FaRegChartBar,
    FaCommentAlt,
    FaShoppingBag,
    FaThList,
}from "react-icons/fa";
import { IoIosSync} from "react-icons/io";
import { NavLink } from 'react-router-dom';
import { FcRating } from "react-icons/fc";
import { MdOutlineElectricalServices,MdLogout } from "react-icons/md";
import { GrAdd } from "react-icons/gr";

import './css/Sidebar.css'
const Sidebar = ({children}) => {
    const[isOpen ,setIsOpen] = useState(false);
    const toggle = () => setIsOpen (!isOpen);
    const menuItem=[
        {
            path:"/getorders",
            name:"Dashboard",
            icon:<FaTh/>
        },
        {
            path:"/updateOrderStatus",
            name:"Update Orders",
            icon:<IoIosSync/>
        },
        {
            path:"vendor/:id",
            name:"Vendor Details",
            icon:<FaUserAlt/>
        },
        {
            path:"/rating",
            name:"Rating",
            icon:<FcRating/>
        },
        {
            path:"/addService",
            name:"Add Services",
            icon:<GrAdd/>
        },
        {
            path:"/serviceDetails",
            name:"Service Details",
            icon:<MdOutlineElectricalServices/>
        },
        {
            path:"/logoutVendor",
            name:"Log Out",
            icon:<MdLogout/>
        }
        
    ]
    return (
        <div className="container">
           <div style={{width: isOpen ? "200px" : "50px"}} className="sidebar">
               <div className="top_section">
                   <h1 style={{display: isOpen ? "block" : "none"}} className="logo">Vendor</h1>
                   <div style={{marginLeft: isOpen ? "50px" : "0px"}} className="bars">
                       <FaBars onClick={toggle}/>
                   </div>
               </div>
               {
                   menuItem.map((item, index)=>(
                       <NavLink to={item.path} key={index} className="link" activeclassName="active">
                           <div className="icon">{item.icon}</div>
                           <div style={{display: isOpen ? "block" : "none"}} className="link_text">{item.name}</div>
                       </NavLink>
                   ))
               }
           </div>
           <main>{children}</main>
        </div>
    );
};

export default Sidebar;