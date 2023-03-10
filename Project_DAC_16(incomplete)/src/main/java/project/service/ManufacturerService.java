package project.service;

import java.util.List;

import project.entity.Manufacturer;
import project.entity.Request;
import project.response.ApiResponse;
import project.response.EmailRequestDto;
import project.response.LoginRequestDto;
import project.response.ManufacturerSpecificResp;

public interface ManufacturerService {

	Manufacturer addManufactrer(Manufacturer m);

	ManufacturerSpecificResp getManufacturerDetails(String name);

	List<Manufacturer> getAllManufacturers();

	ManufacturerSpecificResp authenticateManufacturer(LoginRequestDto dto);

	ApiResponse updatePassword(LoginRequestDto dto);

	ApiResponse updateManufacturer(ManufacturerSpecificResp manufacturer);

	ManufacturerSpecificResp getManufacturerByMail(EmailRequestDto dto);

	List<Request> requestsForManufacturer(ManufacturerSpecificResp manufacturer);

}
