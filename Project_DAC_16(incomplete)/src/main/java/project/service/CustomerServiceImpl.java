package project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.entity.Customer;
import project.exception.ResourceNotFoundException;
import project.repository.CustomerRepository;
import project.response.ApiResponse;
import project.response.CustomerSpecificResp;
import project.response.EmailRequestDto;
import project.response.LoginRequestDto;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public CustomerSpecificResp authenticateCustomer(LoginRequestDto dto) {
		Customer customer = customerRepo.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Email ID or Password"));
		return mapper.map(customer, CustomerSpecificResp.class);
	}

	@Override
	public CustomerSpecificResp getCustomerByMail(EmailRequestDto dto) {
		Customer customer = customerRepo.findByEmail(dto.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Email ID or Password"));
		return mapper.map(customer, CustomerSpecificResp.class);
	}

	@Override
	public ApiResponse updatePassword(LoginRequestDto dto) {
		int res = customerRepo.updateCustomerSetPasswordForEmail(dto.getPassword(), dto.getEmail());
		if (res == 1) {
			return new ApiResponse("Password Reset Successful");
		}
		throw new ResourceNotFoundException("Password Reset Unsuccessful");
	}

	@Override
	public ApiResponse updateCustomer(CustomerSpecificResp customer) {
		int res = customerRepo.updateCustomerSetCustomerForId(customer.getFirstName(), customer.getLastName(),
				customer.getEmail(), customer.getMobileNumber(), customer.getId());
		if (res == 1) {
			return new ApiResponse("Update Successful");
		}
		throw new ResourceNotFoundException("Update Unsuccessful");
	}

}
