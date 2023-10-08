package com.example.sogong.domain.cart_product.repository;

import com.example.sogong.domain.cart_product.domain.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, Long>
{

}
