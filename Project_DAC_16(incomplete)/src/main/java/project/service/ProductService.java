package project.service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.springframework.http.ResponseEntity;

import project.entity.Product;
import project.response.ProductInsertDto;
import project.response.ProductUpdateDto;

public interface ProductService 
{
	public List<Product> getAllProducts();
	
	public ResponseEntity<?> saveProductDetails(ProductInsertDto product);
	
	public ResponseEntity<?> deleteProduct(Long productId);
	
	public ResponseEntity<?> getProductById(Long productId);
	
	public ResponseEntity<?> updateProduct(ProductUpdateDto productUpdateDto);
	
	public InputStream getResource(String fileName) throws FileNotFoundException;
	
}
