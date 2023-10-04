package com.example.sogong.global.auth.forbidden_token;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.Objects;

@ToString
@RedisHash(value = "forbidden_token")
@Getter
public class ForbiddenToken {
    @Id
    private final String accessToken;

    private final long memberId;

    @TimeToLive
    private final long timeToLive;

    @Builder
    protected ForbiddenToken(String accessToken, long memberId, long timeToLive) {
        this.accessToken = accessToken;
        this.memberId = memberId;
        this.timeToLive = timeToLive;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForbiddenToken that = (ForbiddenToken) o;
        return memberId == that.memberId && Objects.equals(accessToken, that.accessToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, memberId);
    }
}
