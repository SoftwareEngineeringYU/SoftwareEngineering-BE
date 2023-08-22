package com.example.sogong.global.auth;

public class AuthConstants {
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String SECURITY_REQUIREMENT = TOKEN_PREFIX + AUTH_HEADER;

    private AuthConstants() {}
}
