import { Outlet } from "react-router-dom";
import NavBar from "./NavBar";


const CustomerPage = () => {
    return (
        <div>
            <NavBar />
            <h1>Customer Show Data</h1>
            <Outlet/>
        </div>
    );
}

export default CustomerPage;