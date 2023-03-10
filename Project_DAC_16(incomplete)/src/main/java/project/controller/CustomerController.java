package project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.entity.Customer;
import project.entity.Request;
import project.response.ApiResponse;
import project.response.CustomerSpecificResp;
import project.response.EmailRequestDto;
import project.response.LoginRequestDto;
import project.service.CustomerService;
import project.service.RequestService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customer")
@Validated
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RequestService requestService;

//	@Autowired
//	private EmailService emailService;

	@PostMapping("/register")
	public Customer saveCustomer(@RequestBody @Valid Customer customer) {
		return customerService.addCustomer(customer);
	}

	@GetMapping("/all")
	public List<Customer> fetchAllCustomers() {
		return customerService.getAllCustomers();
	}

	@PostMapping("/signin")
	public ResponseEntity<?> validateEmployee(@RequestBody @Valid LoginRequestDto dto) {
		try {
			System.out.println("");
			return ResponseEntity.ok(customerService.authenticateCustomer(dto));
		} catch (RuntimeException e) {
			System.out.println("err in emp controller " + e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PostMapping("/getbymail")
	public ResponseEntity<?> getCustomerByMail(@RequestBody @Valid EmailRequestDto dto) {
		try {
			return ResponseEntity.ok(customerService.getCustomerByMail(dto));
		} catch (RuntimeException e) {
			System.out.println("err in emp controller " + e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PutMapping("/resetpassword")
	public ResponseEntity<?> resetPassword(@RequestBody @Valid LoginRequestDto dto) {
		try {
			return ResponseEntity.ok(customerService.updatePassword(dto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateCustomer(@RequestBody @Valid CustomerSpecificResp customer) {
		try {
			return ResponseEntity.ok(customerService.updateCustomer(customer));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PostMapping("/request")
	public Request addNewRequest(@RequestBody @Valid Request request) {
		return requestService.addNewRequest(request);
	}

}
