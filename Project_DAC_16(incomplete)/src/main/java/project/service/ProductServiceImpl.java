package project.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import project.autodelete.UnusedImages;
import project.autodelete.UnusedImagesRepository;
import project.entity.Customer;
import project.entity.Manufacturer;
import project.entity.Product;
import project.repository.CustomerRepository;
import project.repository.ManufacturerRepository;
import project.repository.ProductRepository;
import project.repository.RequestRepository;
import project.response.ApiResponse;
import project.response.ProductInsertDto;
import project.response.ProductUpdateDto;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private RequestRepository requestRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private ManufacturerRepository manufacturerRepo;

	@Autowired
	private UnusedImagesRepository unusedImagesRepo;

	@Value("${project.image}")
	private String path;

	@Autowired
	private Environment env;

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = productRepo.findAll();
		return products;
	}

	@Override
	public ResponseEntity<?> saveProductDetails(ProductInsertDto productInsertDto) {
		Product product = new Product();

		Optional<Customer> customerOptional = customerRepo.findById(productInsertDto.getCustomerId());
		Optional<Manufacturer> manufacturerOptional = manufacturerRepo.findById(productInsertDto.getManufacturerId());

		if (customerOptional.isPresent() && manufacturerOptional.isPresent()) {
			try {
				String filePath = uploadImage(productInsertDto.getReceipt());
				String portNumber = env.getProperty("server.port");
				String fullPath = "http://localhost:" + portNumber + "/product/" + filePath;

				product.setCustomer(customerOptional.get());
				product.setManufacturer(manufacturerOptional.get());
				product.setSerialNumber(productInsertDto.getSerialNumber());
				product.setDateOfManufacture(productInsertDto.getDateOfManufacture());
				product.setPurchaseDate(productInsertDto.getPurchaseDate());
				product.setProductName(productInsertDto.getProductName());
				product.setProductType(productInsertDto.getProductType());
				product.setReceipt(fullPath);

				product = productRepo.save(product);
				String message = "product added with id: " + product.getId();
				ApiResponse responseDto = new ApiResponse(message);
				return new ResponseEntity<>(responseDto, HttpStatus.OK);
			} catch (IOException e) {
				String message = "receipt upload failed";
				ApiResponse responseDto = new ApiResponse(message);
				return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			String message = "customer ID or Model Id is invalid";
			ApiResponse responseDto = new ApiResponse(message);
			return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> getProductById(Long productId) {
		if (productRepo.existsById(productId)) {
			Product product = productRepo.findById(productId).get();
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else {
			String message = "Product with Id: " + productId + " not found";
			ApiResponse response = new ApiResponse(message);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> updateProduct(ProductUpdateDto productUpdateDto) {
		if (productRepo.existsById(productUpdateDto.getId())) {
			Product product = new Product();
			Optional<Customer> customerOptional = customerRepo.findById(productUpdateDto.getCustomerId());
			Optional<Manufacturer> manufacturerOptional = manufacturerRepo
					.findById(productUpdateDto.getManufacturerId());

			if (customerOptional.isPresent() && manufacturerOptional.isPresent()) {
				try {
					deleteImage(productUpdateDto.getId());

					String filePath = uploadImage(productUpdateDto.getReceipt());
					String portNumber = env.getProperty("server.port");
					String fullPath = "http://localhost:" + portNumber + "/product/" + filePath;
					product.setReceipt(fullPath);

					product.setCustomer(customerOptional.get());
					product.setManufacturer(manufacturerOptional.get());
					product.setId(productUpdateDto.getId());
					product.setSerialNumber(productUpdateDto.getSerialNumber());
					product.setDateOfManufacture(productUpdateDto.getDateOfManufacture());
					product.setPurchaseDate(productUpdateDto.getPurchaseDate());
					product.setProductName(productUpdateDto.getProductName());
					product.setProductType(productUpdateDto.getProductType());

					product = productRepo.save(product);
					String message = "product with id: " + product.getId() + " updated";
					ApiResponse responseDto = new ApiResponse(message);
					return new ResponseEntity<>(responseDto, HttpStatus.OK);
				} catch (IOException e) {
					String message = "receipt upload failed";
					ApiResponse responseDto = new ApiResponse(message);
					return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				String message = "customer Id or Model Id is invalid";
				ApiResponse responseDto = new ApiResponse(message);
				return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
			}
		} else {
			String message = "product with given id doesn't exist";
			ApiResponse responseDto = new ApiResponse(message);
			return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> deleteProduct(Long productId) {
		if (productRepo.existsById(productId)) {
			deleteImage(productId);

			requestRepo.deleteByProduct_Id(productId);
			productRepo.deleteByProduct_Id(productId);

			String message = "Product with Id: " + productId + " deleted";
			ApiResponse responseDto = new ApiResponse(message);
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		} else {
			String message = "Product with Id: " + productId + " not found";
			ApiResponse responseDto = new ApiResponse(message);
			return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
		}
	}

	private String uploadImage(MultipartFile file) throws IOException {
		String actualName = file.getOriginalFilename();
		int indexOfDot = actualName.lastIndexOf(".");
		String fileExtension = actualName.substring(indexOfDot);

		double num = Math.random();
		int randomNum = (int) Math.floor(num * 1000000000);

		String name = randomNum + fileExtension;

		String filePath = path + "/" + name;

		File folder = new File(path);

		if (!folder.exists()) {
			folder.mkdir();
		}

		Files.copy(file.getInputStream(), Paths.get(filePath));

		return filePath;
	}

	private void deleteImage(Long productId) {
		Product existingProduct = productRepo.findById(productId).get();
		String receiptUrl = existingProduct.getReceipt();
		System.out.println(receiptUrl);
		int indexOfLastSlash = receiptUrl.lastIndexOf("/");
		String fileName = receiptUrl.substring(indexOfLastSlash);

		String path = "images" + fileName;
		System.out.println(path);
		File fileToDelete = new File(path);

		boolean isDeleted = fileToDelete.delete();
		if (isDeleted == false) {
			UnusedImages unusedImages = new UnusedImages();
			unusedImages.setPath(path);
			unusedImagesRepo.save(unusedImages);
		}
	}

	@Override
	public InputStream getResource(String fileName) throws FileNotFoundException {
		String fullPath = path + "/" + fileName;
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}
}
