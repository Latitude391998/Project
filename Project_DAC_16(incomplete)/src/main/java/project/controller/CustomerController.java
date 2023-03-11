package project.controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.entity.Customer;
import project.entity.Product;
import project.entity.Request;
import project.response.ApiResponse;
import project.response.CustomerSpecificResp;
import project.response.EmailRequestDto;
import project.response.LoginRequestDto;
import project.response.ProductInsertDto;
import project.response.ProductUpdateDto;
import project.service.CustomerService;
import project.service.ProductService;
import project.service.RequestService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customer")
@Validated
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private RequestService requestService;

	@PostMapping("/register")
	public Customer saveCustomer(@RequestBody @Valid Customer customer) {
		return customerService.addCustomer(customer);
	}

	@GetMapping("/all")
	public List<Customer> fetchAllCustomers() {
		return customerService.getAllCustomers();
	}

	@PostMapping("/register")
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

	@PostMapping("/addrequest")
	public Request addNewRequest(@RequestBody @Valid Request request) {
		return requestService.addNewRequest(request);
	}

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		System.out.println(products);
		return products;
	}

	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestParam("customerId") Long customerId,
			@RequestParam("serialNumber") String serialNumber, @RequestParam("manufacturerId") Long manufacturerId,
			@RequestParam("dateOfManufacture") String dateOfManufacture,
			@RequestParam("purchaseDate") String purchaseDate, @RequestParam("receipt") MultipartFile receipt,
			@RequestParam("productName") String productName, @RequestParam("productType") String productType) {
		System.out.println("in add product");
		ProductInsertDto productInsertDto = new ProductInsertDto();
		productInsertDto.setCustomerId(customerId);
		productInsertDto.setDateOfManufacture(LocalDate.parse(dateOfManufacture));
		productInsertDto.setManufacturerId(manufacturerId);
		productInsertDto.setPurchaseDate(LocalDate.parse(purchaseDate));
		productInsertDto.setReceipt(receipt);
		productInsertDto.setSerialNumber(serialNumber);
		productInsertDto.setProductName(productName);
		productInsertDto.setProductType(productType);
		return productService.saveProductDetails(productInsertDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		return productService.deleteProduct(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@PutMapping("/updateproduct")
	public ResponseEntity<?> updateProduct(@RequestParam("id") Long id, @RequestParam("customerId") Long customerId,
			@RequestParam("serialNumber") String serialNumber, @RequestParam("manufacturerId") Long manufacturerId,
			@RequestParam("dateOfManufacture") String dateOfManufacture,
			@RequestParam("purchaseDate") String purchaseDate, @RequestParam("receipt") MultipartFile receipt,
			@RequestParam("productType") String productType, @RequestParam("productName") String productName) {
		ProductUpdateDto productUpdateDto = new ProductUpdateDto();
		productUpdateDto.setId(id);
		productUpdateDto.setCustomerId(customerId);
		productUpdateDto.setDateOfManufacture(LocalDate.parse(dateOfManufacture));
		productUpdateDto.setManufacturerId(manufacturerId);
		productUpdateDto.setPurchaseDate(LocalDate.parse(purchaseDate));
		productUpdateDto.setReceipt(receipt);
		productUpdateDto.setSerialNumber(serialNumber);
		productUpdateDto.setProductName(productName);
		productUpdateDto.setProductType(productType);
		return productService.updateProduct(productUpdateDto);
	}

	@GetMapping(value = "images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = productService.getResource(imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
