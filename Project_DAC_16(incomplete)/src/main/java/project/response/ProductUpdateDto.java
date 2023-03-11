package project.response;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

public class ProductUpdateDto {
	private Long id;

	private Long customerId;

	private String serialNumber;

	private Long manufacturerId;

	private String productName;

	private String productType;

	private LocalDate dateOfManufacture;

	private LocalDate purchaseDate;

	private MultipartFile receipt;

	public ProductUpdateDto() {
		super();
	}

	public ProductUpdateDto(Long id, Long customerId, String serialNumber, Long manufacturerId, String productName,
			String productType, LocalDate dateOfManufacture, LocalDate purchaseDate, MultipartFile receipt) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.serialNumber = serialNumber;
		this.manufacturerId = manufacturerId;
		this.productName = productName;
		this.productType = productType;
		this.dateOfManufacture = dateOfManufacture;
		this.purchaseDate = purchaseDate;
		this.receipt = receipt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public LocalDate getDateOfManufacture() {
		return dateOfManufacture;
	}

	public void setDateOfManufacture(LocalDate dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public MultipartFile getReceipt() {
		return receipt;
	}

	public void setReceipt(MultipartFile receipt) {
		this.receipt = receipt;
	}

	@Override
	public String toString() {
		return "ProductUpdateDto [id=" + id + ", customerId=" + customerId + ", serialNumber=" + serialNumber
				+ ", manufacturerId=" + manufacturerId + ", productName=" + productName + ", productType=" + productType
				+ ", dateOfManufacture=" + dateOfManufacture + ", purchaseDate=" + purchaseDate + ", receipt=" + receipt
				+ "]";
	}
}
