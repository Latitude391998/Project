import Cards from "./Cards";
import image1 from "../images/image1.jpg"
import { useNavigate } from "react-router-dom";
import NavBar from "./NavBar";

const LandingPage = () => {
    const navigate = useNavigate();
    return (
        <>
            <NavBar />
            <div className="container">
                <h1>Data about Our Website</h1>
                <img src={image1} className="img-fluid" alt="Website drawing" />
                <Cards />
                <div className="card">
                    <div className="card-header">
                        Featured
                    </div>
                    <div className="card-body">
                        <h5 className="card-title">Special title treatment</h5>
                        <p className="card-text">With supporting text below as a natural lead-in to additional content.</p>
                        <button className="btn btn-primary" onClick={() => navigate('*')}>Contact Us</button>
                    </div>
                </div>
            </div>
        </>
    );
}

export default LandingPage;