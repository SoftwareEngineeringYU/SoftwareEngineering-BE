package com.example.sogong.global.filter;

import com.example.sogong.global.auth.jwt.JwtTokenProvider;
import com.example.sogong.global.auth.userdetails.UserDetailsServiceImpl;
import com.example.sogong.global.common.response.code.AuthErrorCode;
import com.example.sogong.global.common.response.code.ErrorCodeUtils;
import com.example.sogong.global.common.response.code.StateCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        try {
            final String jwt = JwtTokenProvider.parseJwtFromRequest(request);

            if (StringUtils.hasText(jwt)) {
                final String subject = jwtTokenProvider.getSubject(jwt);

                final UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
                setAuthentication(request, userDetails);
            }
        } catch (Exception exception) {
            handleError(request, exception);
        }

        filterChain.doFilter(request, response);
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


    private void handleError(final HttpServletRequest request, final Exception exception) {
        final StateCode stateCode = ErrorCodeUtils.determineErrorCodeOrDefault(exception, AuthErrorCode.FAILED_AUTHENTICATION);

    }

}
