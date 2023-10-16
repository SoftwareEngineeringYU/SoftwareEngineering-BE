package com.example.sogong.domain.auth.api;

import com.example.sogong.domain.auth.service.LogoutService;
import com.example.sogong.global.auth.AuthConstants;
import com.example.sogong.global.resolver.access_token.AccessToken;
import com.example.sogong.global.resolver.access_token.AccessTokenInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@RequestMapping("/api/v1/accounts")
public class LogoutController {

    private final LogoutService logoutService;


    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @CookieValue(AuthConstants.REFRESH_TOKEN) String refreshToken,
            @AccessTokenInfo AccessToken accessToken
    ) {
        logoutService.logout(accessToken, refreshToken);
        return ResponseEntity.noContent().build();
    }

}
