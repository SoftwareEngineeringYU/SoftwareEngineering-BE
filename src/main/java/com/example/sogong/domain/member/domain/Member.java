package com.example.sogong.domain.member.domain;

import com.example.sogong.global.common.BaseTimeEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String nickname;

    private String email;

    private String[] roles;

    private Instant created_at;

    private Instant updated_at;

    private Instant last_login_at;
}
