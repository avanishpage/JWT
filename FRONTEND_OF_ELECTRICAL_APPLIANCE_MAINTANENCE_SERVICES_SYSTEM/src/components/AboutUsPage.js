import React from 'react';
import './css/AboutUsPage.css'; 
import { Link } from 'react-router-dom';
const AboutUsPage = () => {
  return (
   
     
    <div className="about-us-container">
      
      <h2>Welcome to ApplianceRevive Techs!</h2>
      <h3>Electrical Appliance's Maintenance and Servicing System</h3>

      <p>
        Our application is a comprehensive solution built using cutting-edge technologies to provide you with the best maintenance and servicing experience for your electrical appliances. We've combined Bootstrap, ReactJS, Spring Boot, and MySQL to create a powerful full-stack platform that ensures your appliances are in their best working condition.
      </p>

      <h3>Our Technology Stack:</h3>
      <ul>
        <li>
          <strong>Frontend:</strong> We use ReactJS along with Bootstrap to create a seamless and user-friendly interface. This ensures a smooth experience while navigating through our application and booking services.
        </li>
        <li>
          <strong>Backend:</strong> Our backend is powered by Spring Boot, enabling efficient data processing and management. With its robust capabilities, we make sure your appliance servicing requests are handled swiftly and accurately.
        </li>
        <li>
          <strong>Database:</strong> We rely on MySQL as our database system. This ensures that your data is stored securely and can be accessed when you need it.
        </li>
      </ul>

      <h3>What We Offer:</h3>
      <p>
        Our platform allows you to effortlessly schedule maintenance and servicing appointments for a variety of electrical appliances, including air conditioners, refrigerators, televisions, and electrical ovens. By providing us with the necessary details, we ensure that a skilled serviceman from your local area is assigned to handle your servicing needs.
      </p>

      <h3>Why Choose Us:</h3>
      <ul>
        <li>
          <strong>User-Friendly:</strong> Our application is designed with your convenience in mind. Navigating through the booking process and managing your requests is intuitive and straightforward.
        </li>
        <li>
          <strong>Efficiency:</strong> We understand the importance of timely servicing. Our system is optimized to quickly process your requests and assign servicemen, ensuring minimal downtime for your appliances.
        </li>
        <li>
          <strong>Scalability:</strong> As your needs grow, our platform grows with you. We've designed our system to be scalable, so we can continue to provide top-notch service as more customers join our community.
        </li>
      </ul>

      <p>
        At Electrical Appliance's Maintenance and Servicing System, our goal is to provide a streamlined experience that benefits both our customers and our dedicated servicemen. We're committed to ensuring your appliances are in optimal condition, so you can enjoy hassle-free usage.
      </p>

      <p>Thank you for choosing us for your electrical appliance maintenance and servicing needs.</p>
      <p></p>

      <Link to="/">Go to Home</Link>

    </div>
    
  );
};

export default AboutUsPage;
