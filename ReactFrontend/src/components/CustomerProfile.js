import { useNavigate } from "react-router-dom";

const CustomerProfile = () => {

    const navigate = useNavigate();

    const customer = localStorage.getItem("customer_info");

    return (
        <>
        <div>
            <h1>{customer.firstName}</h1>
            <h1>{customer.lastName}</h1>
            <h2>{customer.address}</h2>
            <h2>{customer.email}</h2>

            <h2>{customer.mobileNumber}</h2>
        </div>
        <div >
         {/* <button onClick={() => navigate('manufacturer/customers')}>View Customer</button> */}
            <button onClick={() => navigate('customer/addProduct')}>View Requests</button>
            <button onClick={() => navigate('customer/viewProducts')}>View Requests</button>
            
        </div>
        
        </>

    )
}

export default CustomerProfile;