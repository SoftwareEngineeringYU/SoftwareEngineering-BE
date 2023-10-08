package com.example.sogong.global.auth.forbidden_token;

import com.example.sogong.global.resolver.access_token.AccessToken;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@Service
public class ForbiddenTokenService {

    private final ForbiddenTokenRepository forbiddenTokenRepository;

    public void ban(final AccessToken accessToken) {
        final Date now = new Date();

        final long timeDifferenceMillis = accessToken.expiryDate().getTime() - now.getTime();
        final long timeToLive = TimeUnit.MILLISECONDS.toSeconds(timeDifferenceMillis);

        final var forbiddenToken = ForbiddenToken.builder()
                .accessToken(accessToken.accessToken())
                .memberId(accessToken.subject())
                .timeToLive(timeToLive)
                .build();

        forbiddenTokenRepository.save(forbiddenToken);
        log.info("토큰 차단 등록에 성공. Member ID: {}", forbiddenToken.getMemberId());
    }

    public boolean isBanned(final String accessToken) {
        return forbiddenTokenRepository.existsById(accessToken);
    }

}
