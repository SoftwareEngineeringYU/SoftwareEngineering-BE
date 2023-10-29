package com.example.sogong.global.common.response.code;

import com.example.sogong.global.common.response.exception.AuthErrorException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorCodeUtils {

    private final static Map<Class<? extends Exception>, AuthErrorCode> errorCodeMap = Map.of(
            ExpiredJwtException.class, AuthErrorCode.EXPIRED_ACCESS_TOKEN,
            MalformedJwtException.class, AuthErrorCode.MALFORMED_ACCESS_TOKEN,
            SignatureException.class, AuthErrorCode.TAMPERED_ACCESS_TOKEN,
            UnsupportedJwtException.class, AuthErrorCode.UNSUPPORTED_JWT_TOKEN
    );

    public static AuthErrorCode mappingAuthErrorCodeOrDefault(final JwtException exception, final AuthErrorCode defaultErrorCode) {
        return errorCodeMap.getOrDefault(exception.getClass(), defaultErrorCode);
    }

    public static StateCode determineErrorCodeOrDefault(final Exception exception, final StateCode defaultErrorCode) {

        if (exception instanceof AuthErrorException authErrorException) {
            return authErrorException.getErrorCode();
        } else {
            return defaultErrorCode;
        }
    }

}
