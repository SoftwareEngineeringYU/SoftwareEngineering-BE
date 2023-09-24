package com.example.sogong.global.config.security;

import com.example.sogong.global.auth.forbidden_token.ForbiddenTokenService;
import com.example.sogong.global.auth.jwt.JwtTokenProvider;
import com.example.sogong.global.auth.userdetails.UserDetailsServiceImpl;
import com.example.sogong.global.filter.AuthenticationCheckFilter;
import com.example.sogong.global.filter.TokenBanCheckFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Configuration
class SecurityFilterConfig {

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtTokenProvider jwtTokenProvider;
    private final ForbiddenTokenService forbiddenTokenService;

    private final ObjectMapper objectMapper;

    @Bean
    public AuthenticationCheckFilter authenticationCheckFilter() {
        return new AuthenticationCheckFilter(userDetailsService, jwtTokenProvider);
    }

    @Bean
    public TokenBanCheckFilter tokenBanCheckFilter() {
        return new TokenBanCheckFilter(forbiddenTokenService, objectMapper);
    }

}
