package com.example.sogong.domain.review.repository;

import com.example.sogong.domain.review.domain.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.product.id=:productId")
    List<Review> findAllByProductId(Long productId, Pageable pageable);
}
