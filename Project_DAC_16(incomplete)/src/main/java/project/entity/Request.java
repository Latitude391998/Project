package project.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Request")
public class Request extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;

	private LocalDate startDate;

	private LocalDate endDate;

	@Enumerated(EnumType.STRING)
	private Type type;

	@Enumerated(EnumType.STRING)
	private Status status;

	public Request() {
		super();
	}

	public Request(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public Request(Customer customer, Product product, Manufacturer manufacturer, LocalDate startDate,
			LocalDate endDate, Status status) {
		super();
		this.customer = customer;
		this.product = product;
		this.manufacturer = manufacturer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Request [customer=" + customer + ", product=" + product + ", manufacturer=" + manufacturer
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + "]";
	}

}
