import { useRef, useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../API/axios";
import AuthContext from "../context/AuthProvider"

const CustomersOfManufacturer = () => {
    //const userRef = useRef();
    //const { setAuth } = useContext(AuthContext);

    var customerList;
    const manufacturer = localStorage.getItem("manufacturer_info");
    const [msg, setMsg] = useState("");

    useEffect(() => {
        try {
            const response = axios.get("/manufacturer/customers", JSON.stringify(manufacturer),
                {
                    headers: { "Content-Type": 'application/json' }
                });
                
            
                localStorage.setItem("manufacturer_customer_list",JSON.stringify((response).data));

        } catch (error) {
            setMsg("Invalid Email Id or Password");
        }

        customerList = localStorage.getItem("manufacturer_customer_list");
    }, []);


    return (
        <>
        <table>

            <tr>
                <th>Name</th>
                <th>Email</th>
            </tr>
            <tbody>
                {customerList.map((item) => {
                    <tr>
                        <td>{item.firstName} {item.lastName}</td>
                        <td> {item.email}</td>
                    </tr>
                })}
            </tbody>
          
        </table>   
        </>
    );
}

export default CustomersOfManufacturer;
