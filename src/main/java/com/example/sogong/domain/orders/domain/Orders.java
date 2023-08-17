package com.example.sogong.domain.orders.domain;

import com.example.sogong.global.common.BaseTimeEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

public class Orders extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long buyer_id;

    private String address;

    private String cs_status;

    private String order_status;

    private String zip_code;

    private Instant created_at;

    private Instant updated_at;

    private Instant purchased_at;
}
