package com.example.sogong.global.config.security;

import com.example.sogong.global.filter.AuthenticationCheckFilter;
import com.example.sogong.global.filter.TokenBanCheckFilter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Configuration
class SecurityAdapterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final DaoAuthenticationProvider authenticationProvider;

    private final TokenBanCheckFilter tokenBanCheckFilter;
    private final AuthenticationCheckFilter authenticationCheckFilter;


    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authenticationProvider(authenticationProvider);
        httpSecurity.addFilterBefore(tokenBanCheckFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(authenticationCheckFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
