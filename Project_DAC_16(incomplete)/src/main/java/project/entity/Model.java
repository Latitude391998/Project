package project.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Model")
public class Model extends BaseEntity {

	private String name;

	private String type;

	@ManyToOne
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;

	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> productList = new ArrayList<Product>();

	public Model() {
		super();
	}

	public Model(String name, String type, Manufacturer manufacturer, List<Product> productList) {
		super();
		this.name = name;
		this.type = type;
		this.manufacturer = manufacturer;
		this.productList = productList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	@Override
	public String toString() {
		return "Model [name=" + name + ", type=" + type + ", manufacturer=" + manufacturer + ", productList="
				+ productList + "]";
	}

}
