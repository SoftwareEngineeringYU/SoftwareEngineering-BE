package com.example.sogong.domain.auth.payload;

import com.example.sogong.global.auth.AuthConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record TokenRefreshResponse(
        @NotBlank
        String accessToken,
        @NotBlank
        String refreshToken,
        String type
) {
    @Builder
    public TokenRefreshResponse {
        type = AuthConstants.TOKEN_PREFIX;
    }
}
