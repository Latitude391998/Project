package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>
{
	@Modifying
	@Query("delete from Product p where p.id = :productId")
	void deleteByProduct_Id(@Param("productId") Long productId);
}
