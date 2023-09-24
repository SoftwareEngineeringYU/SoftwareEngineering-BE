package com.example.sogong.global.auth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum AuthConstants {

    AUTH_HEADER("Authorization"),
    TOKEN_PREFIX("Bearer "),

    ;

    private final String value;
}
