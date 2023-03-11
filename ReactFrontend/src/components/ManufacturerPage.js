import { Outlet } from "react-router-dom";
import NavBar from "./NavBar";
const ManufacturerPage = () => {
    return (
        <div>
            <NavBar />
            <h1>Manufacturer Show Data</h1>
            <Outlet />
        </div>
    );
}

export default ManufacturerPage;