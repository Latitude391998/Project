package project.autodelete;

import javax.persistence.Entity;
import javax.persistence.Table;

import project.entity.BaseEntity;

@Entity
@Table(name = "unused_images")
public class UnusedImages extends BaseEntity {
	private String path;

	public UnusedImages() {
		super();
	}

	public UnusedImages(String path) {
		super();
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "UnusedImages [path=" + path + "]";
	}
}
