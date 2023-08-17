package com.example.sogong.domain.product_image.domain;

import com.example.sogong.global.common.BaseTimeEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

public class ProductImage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long product_id;

    private String image_name;

    private String image_path;

    private Instant created_at;

    private Instant updated_at;
}
