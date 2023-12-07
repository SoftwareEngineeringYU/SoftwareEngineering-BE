package com.example.sogong.domain.product.repository;

import com.example.sogong.domain.product.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN FETCH p.images WHERE p.id=:id")
    Optional<Product> findByIdWithImage(Long id);

    @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.category.code=:code")
    List<Product> findAllByCategoryCode(String code, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findAllByName(String name, Pageable pageable);
}
