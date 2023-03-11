package project.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Product")
public class Product extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonManagedReference
	private Customer customer;

	@Column(name = "serial_number", unique = true, nullable = false)
	private String serialNumber;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_type")
	private String productType;

	@ManyToOne
	@JoinColumn(name = "manufacturer_id")
	@JsonManagedReference
	private Manufacturer manufacturer;

	private LocalDate dateOfManufacture;

	private LocalDate purchaseDate;

	private String receipt;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private Request request;

	public Product() {
		super();
	}

	public Product(Customer customer, String serialNumber, String productName, String productType,
			Manufacturer manufacturer, LocalDate dateOfManufacture, LocalDate purchaseDate, String receipt,
			Request request) {
		super();
		this.customer = customer;
		this.serialNumber = serialNumber;
		this.productName = productName;
		this.productType = productType;
		this.manufacturer = manufacturer;
		this.dateOfManufacture = dateOfManufacture;
		this.purchaseDate = purchaseDate;
		this.receipt = receipt;
		this.request = request;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
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

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "Product [customer=" + customer + ", serialNumber=" + serialNumber + ", productName=" + productName
				+ ", productType=" + productType + ", manufacturer=" + manufacturer + ", dateOfManufacture="
				+ dateOfManufacture + ", purchaseDate=" + purchaseDate + ", receipt=" + receipt + ", request=" + request
				+ "]";
	}

}
