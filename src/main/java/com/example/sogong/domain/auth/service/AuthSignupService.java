package com.example.sogong.domain.auth.service;

import com.example.sogong.domain.auth.dto.request.SignupRequest;
import com.example.sogong.domain.auth.dto.response.SignupResult;
import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.member.domain.MemberRole;
import com.example.sogong.domain.member.repository.MemberRepository;
import com.example.sogong.global.common.response.code.ErrorCode;
import com.example.sogong.global.common.response.exception.GlobalErrorException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@Service
public class AuthSignupService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public SignupResult signup(final SignupRequest signupRequest) {

        checkDuplication(signupRequest);

        final var roles = Set.of(MemberRole.USER);

        final var member = Member.builder()
                .email(signupRequest.email())
                .nickname(signupRequest.nickname())
                .roles(roles)
                .password(encodePassword(signupRequest.password()))
                .build();
        memberRepository.save(member);

        log.info("회원가입 완료: ID: {}, 이메일: {}, 닉네임: {}", member.getId(), member.getEmail(), member.getNickname());

        return new SignupResult();
    }


    private void checkDuplication(final SignupRequest signupRequest) {
        final boolean isDuplicated = memberRepository.existsByEmailOrNickname(signupRequest.email(), signupRequest.nickname());

        if (isDuplicated) {
            log.error("이메일이나 닉네임이 중복입니다. Email: {}, Nickname: {}", signupRequest.email(), signupRequest.nickname());
            throw new GlobalErrorException(ErrorCode.DUPLICATE_EMAIL_OR_NICKNAME_ERROR);
        }
    }

    private String encodePassword(final String password) {
        return passwordEncoder.encode(password);
    }

}
