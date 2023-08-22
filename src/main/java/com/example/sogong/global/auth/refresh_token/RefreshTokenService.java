package com.example.sogong.global.auth.refresh_token;

import com.example.sogong.global.auth.forbidden_token.ForbiddenTokenService;
import com.example.sogong.global.exception.auth.AuthErrorCode;
import com.example.sogong.global.exception.auth.AuthErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;


@Slf4j
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final Duration refreshTokenExpiration;
    private final ForbiddenTokenService forbiddenTokenService;

    public RefreshTokenService(
            @Value("${app.token.refresh.expiration}") Duration refreshTokenExpiration,
            RefreshTokenRepository refreshTokenRepository,
            ForbiddenTokenService forbiddenTokenService
    ) {
        this.refreshTokenExpiration = refreshTokenExpiration;
        this.refreshTokenRepository = refreshTokenRepository;
        this.forbiddenTokenService = forbiddenTokenService;
    }


    /**
     * 리프래시 토큰을 새로 발행
     *
     * @param id - 리프래시 토큰 구분 대상
     * @return - 리프래시 토큰
     */
    public String issueRefreshToken(long id) {
        final var refreshToken = RefreshToken.builder()
                .token(makeRefreshToken())
                .memberId(id)
                .timeToLive(getExpiryTime())
                .build();

        refreshTokenRepository.save(refreshToken);

        log.info("리프래시 토큰 신규 발급. Member ID: {}", id);
        return refreshToken.getToken();
    }

    /**
     * 기존의 Refresh 토큰으로 새로운 Refresh 토큰을 발급하는 함수
     *
     * @param requestRefreshToken - 기존의 리프래시 토큰
     * @return - 새로운 리프래시 토큰
     */
    public RefreshToken refresh(String requestRefreshToken) {
        final var refreshToken = findOrThrow(requestRefreshToken);
        refreshToken.refresh(makeRefreshToken(), getExpiryTime());
        return refreshToken;
    }

    public void logout(final String accessToken, long currentMemberId, String requestRefreshToken) {

        final var refreshToken = findOrThrow(requestRefreshToken);

        log.debug("현재 접속한 ID: {}, 토큰내 Member ID: {}", currentMemberId, refreshToken.getMemberId());

        final long expectedMemberId = refreshToken.getMemberId();

        if (expectedMemberId != currentMemberId) {
            final String errorMessage = String.format("비정상적인 로그아웃 시도! 현재 멤버 ID: %s, But tried ID: %s", currentMemberId, expectedMemberId);
            log.warn(errorMessage);
            throw new AuthErrorException(AuthErrorCode.MISMATCHED_REFRESH_TOKEN, errorMessage);
        }

        forbiddenTokenService.register(accessToken, currentMemberId);

        refreshTokenRepository.delete(refreshToken);
        log.info("로그아웃: ID: {}", currentMemberId);
    }

    private String makeRefreshToken() {
        return UUID.randomUUID().toString();
    }

    private long getExpiryTime() {
        return refreshTokenExpiration.toMillis();
    }

    private RefreshToken findOrThrow(String refreshToken) {
        return refreshTokenRepository.findById(refreshToken)
                .orElseThrow(() -> new AuthErrorException(AuthErrorCode.REFRESH_TOKEN_NOT_FOUND, "인증 정보를 찾을 수 없습니다"));
    }

}
