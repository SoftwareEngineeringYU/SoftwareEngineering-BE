package com.example.sogong.global.auth.forbidden_token;

import com.example.sogong.global.auth.jwt.JwtTokenProvider;
import com.example.sogong.global.exception.auth.AuthErrorCode;
import com.example.sogong.global.exception.auth.AuthErrorException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@Service
public class ForbiddenTokenService {
    
    private final ForbiddenTokenRepository forbiddenTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void register(final String accessToken, final long memberId) {
        final Date now = new Date();
        final Date expiryDate = jwtTokenProvider.getExpiryDate(accessToken);

        final long timeDifferenceMillis = expiryDate.getTime() - now.getTime();

        final var forbiddenToken = ForbiddenToken.builder()
                .accessToken(accessToken)
                .memberId(memberId)
                .timeToLive(timeDifferenceMillis)
                .build();

        forbiddenTokenRepository.save(forbiddenToken);
        log.info("토큰 차단 등록에 성공. Member ID: {}", forbiddenToken.getMemberId());
    }
    
    public void checkForbidden(final String accessToken) {
        if (forbiddenTokenRepository.existsById(accessToken))
            throw new AuthErrorException(AuthErrorCode.FORBIDDEN_ACCESS_TOKEN, accessToken);
    }

}
