package com.example.sogong.domain.member.dto.request;

import com.example.sogong.domain.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberRequestDto
{
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(unique = true, nullable = false)
    private String email;

    public MemberRequestDto(Member member) {
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.password=member.getPassword();
    }
}