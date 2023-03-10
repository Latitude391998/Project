package project.response;

import java.time.LocalDate;

import project.entity.Status;
import project.entity.Type;

public class RequestDto {
	private Long customerId;
	private Long manufacturerId;
	private Long productId;
	private LocalDate date;
	private Type type;
	private Status status;

	public RequestDto() {
		super();
	}

	public RequestDto(Long customerId, Long manufacturerId, Long productId, LocalDate date, Type type, Status status) {
		super();
		this.customerId = customerId;
		this.manufacturerId = manufacturerId;
		this.productId = productId;
		this.date = date;
		this.type = type;
		this.status = status;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RequestDto [customerId=" + customerId + ", manufacturerId=" + manufacturerId + ", productId="
				+ productId + ", date=" + date + ", type=" + type + ", status=" + status + "]";
	}

}
