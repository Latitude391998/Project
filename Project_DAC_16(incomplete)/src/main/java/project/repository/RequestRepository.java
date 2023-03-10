package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.entity.Manufacturer;
import project.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
	
	List<Request> findByManufacturer(Manufacturer manufacturer);
	
}