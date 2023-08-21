package com.example.sogong.domain.product.dao;

import com.example.sogong.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepository extends JpaRepository<Product, Long> {
}
