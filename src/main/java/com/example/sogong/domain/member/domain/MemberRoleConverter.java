package com.example.sogong.domain.member.domain;

import com.example.sogong.global.common.util.converter.AbstractLegacyEnumAttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class MemberRoleConverter extends AbstractLegacyEnumAttributeConverter<MemberRole> {
    private static final String ENUM_NAME = "유저권한";

    public MemberRoleConverter() {
        super(MemberRole.class, false, ENUM_NAME);
    }
}
