import React from "react";
import './SliderHome.css'
import { Carousel } from 'react-bootstrap';
function SliderHome() {
    return (


        <Carousel>

            <Carousel.Item>
                <img
                    className="d-block w-100"
                    src="../images/HomeBackground.jpg"
                    alt="First slide"
                />
                <Carousel.Caption>
                    <h3>Restoring Performance: Our Expert Technicians at Work</h3>
                </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item>
                <img
                    className="d-block w-100"
                    src="../images/AC.jpg"
                    alt="First slide"
                />
                <Carousel.Caption>
                    <h3>Flat 40% off on first order</h3>
                    <p>Chilling Discounts Ahead: Embrace the cool with our limited-time AC servicing offers!</p>
                </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item>
                <img
                    className="d-block w-100"
                    src="../images/TV.jpg"
                    alt="Second slide"
                />
                <Carousel.Caption>
                    <h3>Up to 50% off</h3>
                    <p>Don't Miss Another Moment: Our TV Repair Gets You Back to Binge-Watching.</p>
                </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item>
                <img
                    className="d-block w-100"
                    src="../images/Laptop.jpg"
                    alt="Third slide"
                />
                <Carousel.Caption>
                    <h3>Up to 25% off</h3>
                    <p>Upgrade Your Experience: Revitalize your laptop with our top-notch services!</p>
                </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item>
                <img
                    className="d-block w-100"
                    src="../images/Microwave.jpg"
                    alt="Fourth slide"
                />
                <Carousel.Caption>
                    <h3>Up to 45% off</h3>
                    <p>Microwave Mastery: Our services ensure your meals are always on point.</p>
                </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item>
                <img
                    className="d-block w-100"
                    src="../images/Pc.jpg"
                    alt="Fifth slide"
                />
                <Carousel.Caption>
                    <h3>Flat 50% off</h3>
                    <p>Your PC's Lifeline: Experience seamless computing with our professional services.</p>
                </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item>
                <img
                    className="d-block w-100"
                    src="../images/Refrigerator.jpg"
                    alt="Sixth slide"
                />
                <Carousel.Caption>
                    <h3>Book our service now!!</h3>
                    <p>Cool Confidence, Guaranteed: Trust our refrigerator services for fresh and flawless performance.</p>
                </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item>
                <img
                    className="d-block w-100"
                    src="../images/WashingMachine.jpg"
                    alt="Seventh slide"
                />
                <Carousel.Caption>
                    <p>Expert Repairs for a Smooth Laundry Experience: Get Your Washing Machine Fixed Today.</p>
                </Carousel.Caption>
            </Carousel.Item>

        </Carousel>

    )
}


export default SliderHome;