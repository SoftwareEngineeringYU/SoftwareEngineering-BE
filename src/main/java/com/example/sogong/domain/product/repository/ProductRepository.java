package com.example.sogong.domain.product.repository;

import com.example.sogong.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN FETCH p.images WHERE p.id=:id")
    Optional<Product> findByIdWithImage(Long id);
}
