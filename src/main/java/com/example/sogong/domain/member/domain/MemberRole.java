package com.example.sogong.domain.member.domain;

import com.example.sogong.global.common.util.converter.LegacyCommonType;
import lombok.Getter;

@Getter
public enum MemberRole implements LegacyCommonType {
    USER("1", "ROLE_USER", "회원"), // 등록된 사용자
    SELLER("2", "ROLE_SELLER", "판매자"), // 판매자
    ADMIN("3", "ROLE_ADMIN", "관리자") // 관리자
    ;

    private final String code;
    private final String key;
    private final String title;

    MemberRole(String code, String key, String title) {
        this.code = code;
        this.key = key;
        this.title = title;
    }

    @Override
    public String getCode() {
        return code;
    }
}
