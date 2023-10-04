package com.example.sogong.domain.auth.api;

import com.example.sogong.domain.auth.payload.request.LoginRequest;
import com.example.sogong.domain.auth.payload.request.SignupRequest;
import com.example.sogong.domain.auth.service.AuthSignupService;
import com.example.sogong.domain.auth.service.AuthTokenService;
import com.example.sogong.global.auth.AuthConstants;
import com.example.sogong.global.common.response.SuccessResponse;
import com.example.sogong.global.common.util.HeaderUtils;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthSignupService authSignupService;
    private final AuthTokenService authTokenService;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {
        final var result = authSignupService.signup(signupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.from(result));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        final var result = authTokenService.login(loginRequest);
        return ResponseEntity.ok()
                .headers(HeaderUtils.createLoginTokenHeaders(result))
                .build();
    }

    @PostMapping("/token")
    public ResponseEntity<?> refreshToken(
            @CookieValue(AuthConstants.REFRESH_TOKEN) String refreshToken
    ) {
        final var result = authTokenService.refreshTokens(refreshToken);
        return ResponseEntity.ok()
                .headers(HeaderUtils.createLoginTokenHeaders(result))
                .build();
    }

}
