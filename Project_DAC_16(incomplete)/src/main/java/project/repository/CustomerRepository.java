package project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByEmail(String email);

	Optional<Customer> findByEmailAndPassword(String email, String password);

	@Modifying
	@Query(value = "update Customer c set c.password = :password where c.email = :email")
	int updateCustomerSetPasswordForEmail(@Param("password") String password, @Param("email") String email);

	@Modifying
	@Query(value = "update Customer c set c.firstName=:firstName, c.lastName=:lastName, c.email=:email,"
			+ " c.mobileNumber=:mobileNumber where c.id=:id")
	int updateCustomerSetCustomerForId(@Param("firstName") String firstName, @Param("lastName") String lastName,
			@Param("email") String email, @Param("mobileNumber") Long mobileNumber, @Param("id") Long id);
}
