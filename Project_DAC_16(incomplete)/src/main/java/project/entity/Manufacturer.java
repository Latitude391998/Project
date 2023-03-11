package project.entity;

import java.util.ArrayList;
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
@Table(name = "Manufacturer")
public class Manufacturer extends BaseEntity {

	@Column(name = "brand_name", nullable = false)
	private String brandName;

	@Embedded
	private Address address;

	@Column(name = "mobile_number", unique = true)
	private long mobileNumber;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "type_of_user")
	@Enumerated(EnumType.STRING)
	private UserType type;

	@OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Request> requestList = new ArrayList<Request>();

	@OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> productList = new ArrayList<Product>();

	public Manufacturer() {
		super();
	}

	public Manufacturer(String brandName, Address address, long mobileNumber, String email, String password,
			UserType type, List<Request> requestList, List<Product> productList) {
		super();
		this.brandName = brandName;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.type = type;
		this.requestList = requestList;
		this.productList = productList;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
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

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public List<Request> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<Request> requestList) {
		this.requestList = requestList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	@Override
	public String toString() {
		return "Manufacturer [brandName=" + brandName + ", address=" + address + ", mobileNumber=" + mobileNumber
				+ ", email=" + email + ", password=" + password + ", type=" + type + ", requestList=" + requestList
				+ ", productList=" + productList + "]";
	}

}
