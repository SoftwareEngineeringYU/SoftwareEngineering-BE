package com.example.sogong.global.filter;

import com.example.sogong.global.auth.forbidden_token.ForbiddenTokenService;
import com.example.sogong.global.auth.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class TokenBanCheckFilter extends OncePerRequestFilter {

    private final ForbiddenTokenService forbiddenTokenService;
    private final ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String jwt = JwtTokenProvider.parseJwtFromRequest(request);

        if (StringUtils.hasText(jwt) && forbiddenTokenService.isBanned(jwt)) {
            responseError(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }


    private void responseError(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // TODO 공통 예외 반환 클래스에 맞춰서 에러 정보 반환

        objectMapper.writeValue(response.getOutputStream(), "에러");
    }

}
