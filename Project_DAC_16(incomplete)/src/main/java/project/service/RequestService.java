package project.service;

import java.util.List;

import project.entity.Request;
import project.response.ManufacturerSpecificResp;

public interface RequestService {
	
	Request addNewRequest(Request service);
	
	List<Request> requestsForManufacturer(ManufacturerSpecificResp manufacturer);
}
