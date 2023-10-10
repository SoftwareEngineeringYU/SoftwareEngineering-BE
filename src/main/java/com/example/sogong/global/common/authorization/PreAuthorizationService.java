package com.example.sogong.global.common.authorization;

import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.member.repository.MemberRepository;
import com.example.sogong.global.common.response.code.ErrorCode;
import com.example.sogong.global.common.response.exception.GlobalErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("preAuthorizeService")
@RequiredArgsConstructor
@Slf4j
public class PreAuthorizationService {
    private final MemberRepository memberRepository;

    public boolean isReviewer(Long requestMemberId, Long reviewId) {
        log.info("requestMemberId = {}, reviewId = {}", requestMemberId, reviewId);

        Member member = memberRepository.findById(requestMemberId).orElseThrow(
                () -> new GlobalErrorException(ErrorCode.NOT_FOUND_ERROR)
        );

        return member.getReviews().stream().anyMatch(review -> review.getId().equals(reviewId));
    }
}
