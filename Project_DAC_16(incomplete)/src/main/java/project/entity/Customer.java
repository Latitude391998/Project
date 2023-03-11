package project.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer extends BaseEntity {

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Embedded
	private Address address;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "mobile_number", unique = true)
	private long mobileNumber;

	@Column(name = "type_of_user")
	@Enumerated(EnumType.STRING)
	private UserType type;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> productList;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Request> requestList;

	public Customer() {
		super();
	}

	public Customer(String firstName, String lastName, Address address, String email, String password,
			long mobileNumber, UserType type, List<Product> productList, List<Request> requestList) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.type = type;
		this.productList = productList;
		this.requestList = requestList;
	}

	public void addProduct(Product p) {
		productList.add(p);
		p.setCustomer(this);
	}

	public void removeProduct(Product p) {
		productList.remove(p);
		p.setCustomer(null);
	}

	public void addRequest(Request r) {
		requestList.add(r);
		r.setCustomer(this);
	}

	public void removeRequest(Request r) {
		requestList.remove(r);
		r.setCustomer(null);
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<Request> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<Request> requestList) {
		this.requestList = requestList;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", email="
				+ email + ", password=" + password + ", mobileNumber=" + mobileNumber + ", type=" + type
				+ ", productList=" + productList + ", requestList=" + requestList + "]";
	}

}
