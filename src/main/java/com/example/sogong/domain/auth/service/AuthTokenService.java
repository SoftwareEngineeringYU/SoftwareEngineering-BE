package com.example.sogong.domain.auth.service;

import com.example.sogong.domain.auth.payload.request.LoginRequest;
import com.example.sogong.domain.auth.payload.response.TokenDto;
import com.example.sogong.global.auth.jwt.JwtTokenProvider;
import com.example.sogong.global.auth.refresh_token.RefreshToken;
import com.example.sogong.global.auth.refresh_token.RefreshTokenService;
import com.example.sogong.global.auth.userdetails.CustomUserDetails;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@Transactional(readOnly = true)
@Service
public class AuthTokenService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;


    public TokenDto login(final LoginRequest loginRequest) {

        final CustomUserDetails userDetails = setAuthentication(loginRequest);

        final Long memberId = userDetails.getId();

        final String accessToken = jwtTokenProvider.issueJwtToken(memberId);
        final String refreshToken = refreshTokenService.issueRefreshToken(memberId);

        log.info("로그인, 토큰 신규 발급. Member ID: {}", userDetails.getId());

        return new TokenDto(accessToken, refreshToken);
    }

    /**
     * 기존의 유효한 Refresh 토큰으로 새로운 Access 토큰과 Refresh 토큰을 발급하는 함수
     *
     * @param requestRefreshToken - 토큰 재발급 정보
     * @return - 재발급된 토클들 반환
     */
    public TokenDto refreshTokens(final String requestRefreshToken) {
        // Refresh 토큰 재발급
        final RefreshToken refreshToken = refreshTokenService.refresh(requestRefreshToken);

        final long memberId = refreshToken.getMemberId();

        // Access Token 재발급
        final String newAccessToken = jwtTokenProvider.issueJwtToken(memberId);

        log.info("JWT, 리프래시 토큰 재발급 완료. Member ID : {}", memberId);

        return new TokenDto(newAccessToken, refreshToken.getToken());
    }


    private CustomUserDetails setAuthentication(final LoginRequest loginRequest) {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return (CustomUserDetails) authentication.getPrincipal();
    }

}
