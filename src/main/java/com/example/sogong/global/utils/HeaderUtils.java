package com.example.sogong.global.utils;

import com.example.sogong.domain.auth.payload.response.TokenDto;
import com.example.sogong.global.auth.AuthConstants;
import org.springframework.http.HttpHeaders;

import java.time.Duration;

public class HeaderUtils {

    public static HttpHeaders createLoginTokenHeaders(final TokenDto tokenDto) {

        final HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, getAccessToken(tokenDto));
        headers.set(HttpHeaders.SET_COOKIE, createRefreshTokenCookie(tokenDto));

        return headers;
    }


    private static String getAccessToken(final TokenDto tokenDto) {
        return tokenDto.type() + tokenDto.accessToken();
    }

    private static String createRefreshTokenCookie(final TokenDto tokenDto) {
        return CookieUtils.createCookie(
                AuthConstants.REFRESH_TOKEN,
                tokenDto.refreshToken(),
                Duration.ofDays(7)
        ).toString();
    }

}
