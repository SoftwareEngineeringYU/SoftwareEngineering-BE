package com.example.sogong.domain.member.domain;

import lombok.Getter;

@Getter
public enum MemberRole {
    USER("ROLE_USER", "회원"), // 등록된 사용자
    SELLER("ROLE_SELLER", "판매자"), // 판매자
    ADMIN("ROLE_ADMIN", "관리자"), // 관리자

    ;

    private final String key;
    private final String title;

    MemberRole(String key, String title) {
        this.key = key;
        this.title = title;
    }
}
