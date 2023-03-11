package project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.entity.Manufacturer;
import project.exception.ResourceNotFoundException;
import project.repository.CustomerRepository;
import project.repository.ManufacturerRepository;
import project.repository.RequestRepository;
import project.response.ApiResponse;
import project.response.EmailRequestDto;
import project.response.LoginRequestDto;
import project.response.ManufacturerSpecificResp;

@Service
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService {

	@Autowired
	private ManufacturerRepository manufacturerRepo;

	@Autowired
	RequestRepository reqRepo;

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Manufacturer addManufactrer(Manufacturer m) {

		return manufacturerRepo.save(m);

	}

	@Override
	public ManufacturerSpecificResp getManufacturerDetails(String brandName) {
		Manufacturer m = manufacturerRepo.findByBrandName(brandName)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Brand Name"));
		return mapper.map(m, ManufacturerSpecificResp.class);
	}

	@Override
	public List<Manufacturer> getAllManufacturers() {
		return manufacturerRepo.findAll();
	}

	@Override
	public ManufacturerSpecificResp authenticateManufacturer(LoginRequestDto dto) {
		Manufacturer m = manufacturerRepo.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Email ID or Password"));
		return mapper.map(m, ManufacturerSpecificResp.class);

	}

	@Override
	public ApiResponse updatePassword(LoginRequestDto dto) {
		int res = manufacturerRepo.updateManufacturerSetPasswordForEmail(dto.getPassword(), dto.getEmail());
		if (res == 1) {
			return new ApiResponse("Password Reset Successful");
		}
		throw new ResourceNotFoundException("Password Reset Unsuccessful");
	}

	@Override
	public ApiResponse updateManufacturer(ManufacturerSpecificResp manufacturer) {
		int res = manufacturerRepo.updateManufacturerSetManufacturerForId(manufacturer.getBrandName(),
				manufacturer.getEmail(), manufacturer.getMobileNumber(), manufacturer.getId());
		if (res == 1) {
			return new ApiResponse("Update Successful");
		}
		throw new ResourceNotFoundException("Update Unsuccessful");
	}

	@Override
	public ManufacturerSpecificResp getManufacturerByMail(EmailRequestDto dto) {
		Manufacturer m = manufacturerRepo.findByEmail(dto.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Email ID or Password"));
		return mapper.map(m, ManufacturerSpecificResp.class);
	}

	@Override
	public Manufacturer getManufacturerById(Long id) {
		// TODO Auto-generated method stub
		Manufacturer m = manufacturerRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Email ID or Password"));
		return m;
	}

}
