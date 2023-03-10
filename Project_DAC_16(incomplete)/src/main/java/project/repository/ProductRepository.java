package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
