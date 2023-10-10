package com.example.sogong.domain.member.service;

import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.member.repository.MemberRepository;
import com.example.sogong.global.common.response.code.ErrorCode;
import com.example.sogong.global.common.response.exception.GlobalErrorException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberSearchService {
    private final MemberRepository memberRepository;

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(
                () -> new GlobalErrorException(ErrorCode.NOT_FOUND_ERROR)
        );
    }
}
