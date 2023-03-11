package project.service;

import java.util.List;

import project.entity.Customer;
import project.response.ApiResponse;
import project.response.CustomerSpecificResp;
import project.response.EmailRequestDto;
import project.response.LoginRequestDto;

public interface CustomerService {

	Customer addCustomer(Customer customer);

	List<Customer> getAllCustomers();

	CustomerSpecificResp authenticateCustomer(LoginRequestDto dto);

	CustomerSpecificResp getCustomerByMail(EmailRequestDto dto);

	ApiResponse updatePassword(LoginRequestDto dto);

	ApiResponse updateCustomer(CustomerSpecificResp customer);
	
	Customer getCustomerById(Long id);
}
