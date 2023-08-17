package com.example.sogong.global.common;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

public abstract class BaseTimeEntity {

    @CreatedDate
    private Instant createAt;

    @LastModifiedDate
    private Instant updateAt;
}
