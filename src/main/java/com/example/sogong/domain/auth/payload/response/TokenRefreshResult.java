package com.example.sogong.domain.auth.payload.response;

import com.example.sogong.global.auth.AuthConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record TokenRefreshResult(
        @NotBlank
        String accessToken,
        @NotBlank
        String refreshToken,
        String type
) {
    @Builder
    public TokenRefreshResult {
        type = AuthConstants.TOKEN_PREFIX.getValue();
    }
}
