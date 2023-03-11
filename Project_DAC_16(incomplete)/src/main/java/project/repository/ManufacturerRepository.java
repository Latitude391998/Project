package project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.entity.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

	Optional<Manufacturer> findByEmail(String email);

	Optional<Manufacturer> findByBrandName(String brandName);

	Optional<Manufacturer> findByEmailAndPassword(String email, String password);

	@Modifying
	@Query(value = "update Manufacturer m set m.password = :password where m.email = :email")
	int updateManufacturerSetPasswordForEmail(@Param("password") String password, @Param("email") String email);

	@Modifying
	@Query(value = "update Manufacturer m set m.brandName=:brandName, m.email=:email,"
			+ " m.mobileNumber=:mobileNumber where m.id=:id")
	int updateManufacturerSetManufacturerForId(@Param("brandName") String brandName, @Param("email") String email,
			@Param("mobileNumber") Long mobileNumber, @Param("id") Long id);

}
