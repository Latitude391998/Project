package project.response;

import project.entity.Address;

public class CustomerSpecificResp {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private long mobileNumber;
	private Address address;

	public CustomerSpecificResp() {
		super();
	}

	public CustomerSpecificResp(Long id, String firstName, String lastName, String email, long mobileNumber,
			Address address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
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
		return "CustomerSpecificResp [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", mobileNumber=" + mobileNumber + ", address=" + address + "]";
	}

}
