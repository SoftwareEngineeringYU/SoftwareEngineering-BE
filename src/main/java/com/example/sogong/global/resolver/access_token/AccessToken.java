package com.example.sogong.global.resolver.access_token;

import java.util.Date;

public record AccessToken(
        String accessToken,
        Long subject,
        Date expiryDate
) {
}
