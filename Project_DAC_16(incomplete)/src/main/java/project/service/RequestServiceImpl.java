package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import project.entity.Manufacturer;
import project.entity.Request;
import project.exception.ResourceNotFoundException;
import project.repository.ManufacturerRepository;
import project.repository.RequestRepository;
import project.response.ManufacturerSpecificResp;

public class RequestServiceImpl implements RequestService {
	
	@Autowired
	RequestRepository requestRepo;
	
	@Autowired
	ManufacturerRepository manufacturerRepo;

	@Override
	public Request addNewRequest(Request request) {
		return requestRepo.save(request);
	}
	
	@Override
	public List<Request> requestsForManufacturer(ManufacturerSpecificResp manufacturer) {

		Long id = manufacturer.getId();

		Manufacturer m = manufacturerRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Manufacturer ID"));

		return requestRepo.findByManufacturer(m);
	}

}
