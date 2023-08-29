import './css/HomePage.css'
// import Slider from './Slider'
import SliderHome from '../Slider/SliderHome';
import Footer from './Footer';
function HomePage() {
    return (
        <div>
            <div className="welcome-container">
                <h1 className="welcome-title">Welcome to ApplianceRevive Techs!</h1>
                <marquee>
                <p className="welcome-text">Your trusted experts in appliance repair and servicing</p>     
                </marquee>         
            </div>
            {/* <Slider></Slider> */}
            <SliderHome></SliderHome>
            <p></p>
            <Footer></Footer>
        </div>
    )
}
export default HomePage;