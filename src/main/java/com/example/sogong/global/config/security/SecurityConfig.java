package com.example.sogong.global.config.security;

import com.example.sogong.global.auth.AuthConstants;
import com.example.sogong.global.auth.entrypoint.AuthEntryPointJwt;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.http.HttpHeaders.SET_COOKIE;


@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Configuration
class SecurityConfig {

    private final AuthEntryPointJwt unauthorizedHandler;

    private final SecurityAdapterConfig securityAdapterConfig;

    @Value("${app.allow-origins:http://localhost:3000}")
    private List<String> corsOrigins;

    private static final String[] publicEndpoints = {
            // API
            "/api/v1/auth/**",
            "/api/v1/test/**",

            // Swagger
            "/v3/api-docs/**",
            "/swagger-ui/**", // /swagger-ui/index.html
            "/swagger-resources/**",
    };

    private static final String[] publicReadOnlyPublicEndpoints = {
            "/api/v1/products/**",
    };


    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> corsConfigurationSource())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations().toString()).permitAll()
                        .requestMatchers(publicEndpoints).permitAll()
                        .requestMatchers(HttpMethod.GET, publicReadOnlyPublicEndpoints).permitAll()
                        .anyRequest().authenticated()
                )

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(auth -> auth
                        .authenticationEntryPoint(unauthorizedHandler)
                );

        httpSecurity.apply(securityAdapterConfig);

        return httpSecurity.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() { // Localhost 환경 cors
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(corsOrigins);
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setMaxAge(3600L); // Cache preflight
        configuration.setExposedHeaders(List.of(SET_COOKIE, HttpHeaders.AUTHORIZATION, AuthConstants.REFRESH_TOKEN));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
