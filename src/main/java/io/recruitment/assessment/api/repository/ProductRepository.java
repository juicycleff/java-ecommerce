package io.recruitment.assessment.api.repository;

import io.recruitment.assessment.api.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM products p WHERE p.name = ?1",
            countQuery = "SELECT count(*) FROM products WHERE name = ?1",
            nativeQuery = true)
    Page<Product> findByName(String name, Pageable pageable);

    @Query("SELECT p FROM products p WHERE p.name LIKE %?1%")
    List<Product> search(String name);
}
