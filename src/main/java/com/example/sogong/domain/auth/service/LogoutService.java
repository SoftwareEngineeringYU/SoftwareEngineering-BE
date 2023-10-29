package com.example.sogong.domain.auth.service;

import com.example.sogong.global.auth.forbidden_token.ForbiddenTokenService;
import com.example.sogong.global.auth.refresh_token.RefreshToken;
import com.example.sogong.global.auth.refresh_token.RefreshTokenRepository;
import com.example.sogong.global.common.response.code.AuthErrorCode;
import com.example.sogong.global.common.response.exception.AuthErrorException;
import com.example.sogong.global.resolver.access_token.AccessToken;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class LogoutService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final ForbiddenTokenService forbiddenTokenService;


    public void logout(final AccessToken accessToken, final String refreshToken) {
        handleLogout(accessToken.subject(), refreshToken);
        forbiddenTokenService.ban(accessToken);
    }

    private void handleLogout(final long currentMemberId, String requestRefreshToken) {

        final var refreshToken = findOrThrow(requestRefreshToken);
        final long expectedMemberId = refreshToken.getMemberId();

        if (expectedMemberId != currentMemberId) {
            final var errorMessage = String.format("비정상적인 로그아웃 시도! 현재 멤버 ID: %s, But tried ID: %s", currentMemberId, expectedMemberId);
            log.warn(errorMessage);
            throw new AuthErrorException(AuthErrorCode.MISMATCHED_REFRESH_TOKEN, errorMessage);
        }

        refreshTokenRepository.delete(refreshToken);
        log.info("로그아웃: ID: {}", currentMemberId);
    }

    private RefreshToken findOrThrow(String refreshToken) {
        return refreshTokenRepository.findById(refreshToken)
                .orElseThrow(() -> new AuthErrorException(AuthErrorCode.REFRESH_TOKEN_NOT_FOUND, "인증 정보를 찾을 수 없습니다"));
    }

}
