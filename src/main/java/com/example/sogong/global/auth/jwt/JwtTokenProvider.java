package com.example.sogong.global.auth.jwt;

import com.example.sogong.global.auth.AuthConstants;
import com.example.sogong.global.common.response.code.ErrorCodeUtils;
import com.example.sogong.global.common.response.code.AuthErrorCode;
import com.example.sogong.global.common.response.exception.AuthErrorException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key tokenSecretKey;
    private final Duration tokenExpiration;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public JwtTokenProvider(
            @Value("${app.token.access.expiration}") Duration accessTime,
            @Value("${app.token.access.secret-key}") String secretKey) {
        this.tokenExpiration = accessTime;
        final byte[] keyBytes = Base64.getEncoder().encode(secretKey.getBytes()); // 비공개 키를 Base64 인코딩
        this.tokenSecretKey = Keys.hmacShaKeyFor(keyBytes);
    }


    public static String parseJwtFromRequest(HttpServletRequest request) {
        final String headerAuth = request.getHeader(AuthConstants.AUTH_HEADER);
        return parseJwt(headerAuth);
    }

    /**
     * 헤더로부터 JWT 토큰을 추출해서 반환
     *
     * @param headerAuth - 헤더 문자열
     * @return - 추출한 JWT 토큰
     */
    private static String parseJwt(final String headerAuth) {

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(AuthConstants.TOKEN_TYPE)) {
            return headerAuth.substring(AuthConstants.TOKEN_TYPE.length()).trim();
        } else {
            return "";
        }
    }

    /**
     * 새로운 JWT 토큰을 발행
     *
     * @param memberId - JWT 토큰 구분 대상
     * @return - 새로 발급한 JWT 토큰
     */
    public String issueJwtToken(final long memberId) {

        final Date now = new Date();
        final Date expiryDate = createExpiryDate(now);

        return Jwts.builder()
                .setHeader(createHeader())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .setClaims(createClaims(memberId, expiryDate))
                .signWith(tokenSecretKey, signatureAlgorithm)
                .compact();
    }

    /**
     * 이상이 없는 토큰인 경우, 해당 토큰 소유 대상 정보를 반환
     *
     * @param accessToken - JWT 인증 토큰
     * @return - 토큰 소유 대상
     */
    public String getSubject(final String accessToken) {
        return verifyAndGetClaims(accessToken).getSubject();
    }

    public Date getExpiryDate(final String accessToken) {
        return verifyAndGetClaims(accessToken).getExpiration();
    }

    public Claims verifyAndGetClaims(final String accessToken) {
        try {
            return getClaimsFormToken(accessToken, tokenSecretKey);
        } catch (JwtException jwtException) {
            final var errorCode = ErrorCodeUtils.mappingAuthErrorCodeOrDefault(jwtException, AuthErrorCode.FAILED_AUTHENTICATION);

            log.warn("Error code : {}, Error - {},  {}",
                    errorCode, jwtException.getClass(), jwtException.getMessage());
            throw new AuthErrorException(errorCode, jwtException.toString());
        }
    }

    private Map<String, Object> createHeader() {
        return Map.of(
                Header.TYPE, Header.JWT_TYPE
        );
    }

    private static Map<String, Object> createClaims(final Long subject, final Date expiryDate) {
        return Map.of(
                Claims.SUBJECT, subject,
                Claims.EXPIRATION, expiryDate
        );
    }

    private Date createExpiryDate(final Date now) {
        return new Date(now.getTime() + tokenExpiration.toMillis());
    }

    private static Claims getClaimsFormToken(final String token, final Key secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
