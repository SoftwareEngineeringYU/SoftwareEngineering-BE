package com.example.sogong.domain.address.domain;

import com.example.sogong.global.common.BaseTimeEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

public class Address extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String zip_code;

    private Long member_id;

    private Instant created_at;

    private Instant updated_at;
}
