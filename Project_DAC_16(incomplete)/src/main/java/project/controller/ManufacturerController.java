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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import project.entity.Manufacturer;
import project.response.ApiResponse;
import project.response.EmailRequestDto;
import project.response.LoginRequestDto;
import project.response.ManufacturerSpecificResp;
import project.service.ManufacturerService;
import project.service.RequestService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/manufacturer")
@Validated
public class ManufacturerController {

	@Autowired
	private ManufacturerService manufacturerService;
	
	@Autowired
	private RequestService requestService;

//	@Autowired
//	private EmailService emailService;

	@PostMapping("/register")
	public Manufacturer addManufacturer(@RequestBody Manufacturer m) {
		System.out.println("added manufacturer: " + m.toString());
		return manufacturerService.addManufactrer(m);

	}

	@GetMapping("/all")
	public List<Manufacturer> getAllManufacturer() {
		return manufacturerService.getAllManufacturers();
	}

	@GetMapping
	@ResponseBody
	public ManufacturerSpecificResp getManufacturerDetails(@RequestParam String brandName) {
		return manufacturerService.getManufacturerDetails(brandName);
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateManufacturer(@RequestBody LoginRequestDto dto) {
		try {
			return ResponseEntity.ok(manufacturerService.authenticateManufacturer(dto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PutMapping("/reset")
	public ResponseEntity<?> resetPassword(@RequestBody @Valid LoginRequestDto dto) {
		try {
			return ResponseEntity.ok(manufacturerService.updatePassword(dto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PostMapping("/getbymail")
	public ResponseEntity<?> getManufacturerByMail(@RequestBody @Valid EmailRequestDto dto) {
		try {
			return ResponseEntity.ok(manufacturerService.getManufacturerByMail(dto));
		} catch (RuntimeException e) {
			System.out.println("err in emp controller " + e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateManufacturer(@RequestBody @Valid ManufacturerSpecificResp manufacturer) {
		try {
			return ResponseEntity.ok(manufacturerService.updateManufacturer(manufacturer));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@GetMapping("/requests")
	public ResponseEntity<?> requestsForManufactrer(@RequestBody ManufacturerSpecificResp manufacturer) {
		try {
			return ResponseEntity.ok(requestService.requestsForManufacturer(manufacturer));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
//	@PutMapping("/request")
//	public 
}
