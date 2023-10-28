package com.example.sogong.global.filter;

import com.example.sogong.global.auth.jwt.JwtTokenProvider;
import com.example.sogong.global.auth.userdetails.UserDetailsServiceImpl;
import com.example.sogong.global.common.response.ErrorResponse;
import com.example.sogong.global.common.response.code.AuthErrorCode;
import com.example.sogong.global.common.response.code.ErrorCodeUtils;
import com.example.sogong.global.common.response.code.StateCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class AuthenticationCheckFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        try {
            doAuthentication(request);
        } catch (Exception exception) {
            handleError(response, exception);
            return;
        }

        filterChain.doFilter(request, response);
    }


    private void doAuthentication(HttpServletRequest request) {
        final String jwt = JwtTokenProvider.parseJwtFromRequest(request);

        if (StringUtils.hasText(jwt)) {
            final String subject = jwtTokenProvider.getSubject(jwt);

            final UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
            setAuthentication(request, userDetails);
        }
    }


    private void setAuthentication(final HttpServletRequest request, final UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    private void handleError(HttpServletResponse response, Exception exception) throws IOException {
        final StateCode stateCode = ErrorCodeUtils.determineErrorCodeOrDefault(exception, AuthErrorCode.FAILED_AUTHENTICATION);

        ErrorResponse errorResponse = new ErrorResponse(stateCode.toString(), stateCode.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }

}
