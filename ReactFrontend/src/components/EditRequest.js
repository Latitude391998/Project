import axios, { Axios } from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

const EditRequest = () => {
    const [request, setRequest] =  useState     ({
        id:"",
        customer:"",
        product:"",
        manufacturer:"",
        startDate:"",
        endDate:"",
        status:""
    });

    const data = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        try {
            const response = Axios.get("/manufacturer/getRequest", JSON.stringify(data.id),
                {
                    headers: { "Content-Type": 'application/json' }
                });
            setRequest(response?.data);
            
        } catch (error) {
            //todo: show error message
        }
    }, []);

    const handleChange = (e) => {
        const value = e.target.value;
        setRequest({...request, [e.target.status]: value});
    };

    const updateRequest = async (e) => {
       try{
        const response = axios.put("/manufacturer/getRequest", JSON.stringify(data.id),
        {
            headers: { "Content-Type": 'application/json' }
        });
        navigate('/manufacturer/profile')
       }catch (error) {
            //todo : show error message
       }
    }

    return (
        <form onSubmit={updateRequest}>
            <div>
                <label >Request ID</label>
                <input type='text' name='id' value={request.id} readOnly />
            </div>
            <div>
                <label >Customer</label>
                <input type='text' name='customer' value={request.customer} readOnly />
            </div>
            <div>
                <label >Manfacturer</label>
                <input type='text' name='manufacturer' value={request.manufacturer} readOnly />
            </div>
            <div>
                <label >Start Date</label>
                <input type='text' name='startdate' value={request.startDate} readOnly />
            </div>
            <div>
                <label >End Date</label>
                <input type='text' name='enddate' value={request.endDate} readOnly />
            </div>
            <div>
                <label >Status</label>
                <input type='text' name='status' value={request.status} onChange={handleChange} />
            </div>

        </form>
    )



}

export default EditRequest;