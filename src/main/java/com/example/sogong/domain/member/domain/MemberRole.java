package com.example.sogong.domain.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum MemberRole {
    USER("ROLE_USER", "회원"), // 등록된 사용자
    SELLER("ROLE_SELLER", "판매자"), // 판매자
    ADMIN("ROLE_ADMIN", "관리자"), // 관리자

    ;

    private final String key;
    private final String title;

    public static Collection<? extends GrantedAuthority> convertToAuthorities(Collection<MemberRole> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getKey()))
                .collect(Collectors.toSet());
    }

}
