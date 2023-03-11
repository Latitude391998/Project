package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import project.entity.Customer;
import project.entity.Manufacturer;
import project.entity.Request;
import project.entity.Status;
import project.exception.ResourceNotFoundException;
import project.repository.ManufacturerRepository;
import project.repository.RequestRepository;
import project.response.ManufacturerSpecificResp;

public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestRepository requestRepo;

	@Autowired
	private EmailService emailService;

	@Autowired
	CustomerService customerService;

	@Autowired
	private ManufacturerRepository manufacturerRepo;

	@Autowired
	ManufacturerService manufacturerService;

	@Override
	public Request addNewRequest(Request request) {
		Manufacturer manufacturer = manufacturerService.getManufacturerById(request.getManufacturer().getId());
		emailService.sendSimpleMessage(manufacturer.getEmail(), "New Request for Product",
				"New Request is Added for Product ID: " + request.getProduct().getId() + ". Please Respond.");
		return requestRepo.save(request);
	}

	@Override
	public List<Request> requestsForManufacturer(ManufacturerSpecificResp manufacturer) {

		Long id = manufacturer.getId();

		Manufacturer m = manufacturerRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Manufacturer ID"));

		return requestRepo.findByManufacturer(m);
	}

	@Override
	public int changeRequestStatus(Status status, Long requestId) {
		Request request = requestRepo.findById(requestId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Reuqest ID !!!!!"));
		Customer customer = customerService.getCustomerById(request.getCustomer().getId());
		emailService.sendSimpleMessage(customer.getEmail(), "Request In Processing",
				"Process started for Request Id: " + requestId + ". Please check.");
		return requestRepo.updateRequestStatus(status, requestId);
	}

}
