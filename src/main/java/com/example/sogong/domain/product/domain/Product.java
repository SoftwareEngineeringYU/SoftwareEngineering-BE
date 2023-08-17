package com.example.sogong.domain.product.domain;

import com.example.sogong.global.common.BaseTimeEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long category_id;

    private Long seller_id;

    private Integer price;

    private Integer stock_amount;

    private String name;

    private String description;

    private Instant created_at;

    private Instant updated_at;
}
