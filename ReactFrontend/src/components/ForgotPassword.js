import { useState } from "react";
import { useNavigate } from "react-router-dom";

import axios from "../API/axios";

const ForgotPassword = () => {
    let response;
    const [email, setEmail] = useState("");
    const [msg, setMsg] = useState("");
    let navigate = useNavigate();
    const clickHandler = () => {
        setMsg("");
    }
    const handleSubmit = async (event) => {
        event.preventDefault();
        try{
        response = await axios.post("/customer/getbymail",JSON.stringify({email}),{
            headers: { "Content-Type": 'application/json' }
        });
        // console.log(response.data);
        localStorage.setItem("customer_info",JSON.stringify((await response).data));
        navigate("/reset");
    }catch(err){
        setMsg("Invalid Email Id or Password");
    } 
    }
    return (
        <div>
             <div className="card-header text-center fs-3">
                    {msg && <p className="text-success">{msg}</p>}
                </div>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="exampleInputEmail1">Email address</label>
                    <input type="email" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" onChange={(e) => setEmail(e.target.value)} required/>
                    <small id="emailHelp" className="form-text text-muted">We'll never share your email with anyone else.</small>
                </div>
                <button type="submit" className="btn btn-primary">Submit</button>
                <input type="reset" className="btn btn-danger" value="Reset" onClick={clickHandler}/>
            </form>
        </div>
    )
}

export default ForgotPassword;