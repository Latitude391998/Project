package project.response;

import project.entity.Address;

public class ManufacturerSpecificResp {
	private Long id;
	private String brandName;
	private String email;
	private Long mobileNumber;
	private Address address;

	public ManufacturerSpecificResp() {
		super();
	}

	public ManufacturerSpecificResp(Long id, String brandName, String email, Long mobileNumber, Address address) {
		super();
		this.id = id;
		this.brandName = brandName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "ManufacturerSpecificResp [id=" + id + ", brandName=" + brandName + ", email=" + email
				+ ", mobileNumber=" + mobileNumber + ", address=" + address + "]";
	}

}
