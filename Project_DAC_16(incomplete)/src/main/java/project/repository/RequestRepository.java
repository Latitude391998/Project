package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.entity.Manufacturer;
import project.entity.Request;
import project.entity.Status;

public interface RequestRepository extends JpaRepository<Request, Long> {
	
	List<Request> findByManufacturer(Manufacturer manufacturer);
	
	@Modifying
	@Query("delete from Request r where r.product.id = :productId")
	void deleteByProduct_Id(@Param("productId") Long productId);
	
	@Modifying
	@Query(value = "update Request r set r.status = :status where r.id =:requestId")
	int updateRequestStatus(@Param("status") Status status, @Param("requestId") Long requestId);
	
}