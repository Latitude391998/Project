import { useNavigate } from "react-router-dom";

const ManufacturerProfile = () => {

    const navigate = useNavigate();

    const manufacturer = localStorage.getItem("manufacturer_info");



    return (
        <>
        <div>
            <h1>{manufacturer.brandName}</h1>
            <h2>{manufacturer.address}</h2>
            <h2>{manufacturer.email}</h2>

            <h2>{manufacturer.mobileNumber}</h2>
        </div>
        <div >
         {/* <button onClick={() => navigate('manufacturer/customers')}>View Customer</button> */}
            <button onClick={() => navigate('manufacturer/requests')}>View Requests</button>
          <button onClick={() => navigate('manufacturer/services')}>View Service</button>
        </div>
        
        </>

    )
}

export default ManufacturerProfile;