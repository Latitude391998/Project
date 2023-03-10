package project.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "serial_number", unique = true, nullable = false)
	private String serialNumber;

	@ManyToOne
	@JoinColumn(name = "model_id")
	private Model model;

	private LocalDate dateOfManufacture;

	private LocalDate purchaseDate;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Request request;


	public Product() {
		super();
	}

	public Product(Customer customer, String serialNumber, Model model, LocalDate dateOfManufacture,
			LocalDate purchaseDate, Request request) {
		super();
		this.customer = customer;
		this.serialNumber = serialNumber;
		this.model = model;
		this.dateOfManufacture = dateOfManufacture;
		this.purchaseDate = purchaseDate;
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

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
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

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "Product [customer=" + customer + ", serialNumber=" + serialNumber + ", model=" + model
				+ ", dateOfManufacture=" + dateOfManufacture + ", purchaseDate=" + purchaseDate + ", request=" + request
				+ "]";
	}



}
