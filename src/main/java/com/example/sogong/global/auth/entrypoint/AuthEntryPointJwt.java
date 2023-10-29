package com.example.sogong.global.auth.entrypoint;

import com.example.sogong.global.common.response.ErrorResponse;
import com.example.sogong.global.common.response.code.AuthErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {

        ErrorResponse errorResponse = makeErrorResponse(authException);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }

    private ErrorResponse makeErrorResponse(AuthenticationException authException) {
        if (authException == null) {
            AuthErrorCode errorCode = AuthErrorCode.FAILED_AUTHENTICATION;
            return new ErrorResponse(errorCode.name(), errorCode.getMessage());
        }

        return new ErrorResponse(authException.toString(), authException.getMessage());
    }

}
