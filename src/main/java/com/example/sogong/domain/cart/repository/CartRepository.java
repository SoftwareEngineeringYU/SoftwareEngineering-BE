package com.example.sogong.domain.cart.repository;

import com.example.sogong.domain.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findByOwnerId(Long userId);
    boolean existsByOwnerIdAndProductId(Long userId, Long productId);
}
