package com.example.sogong.domain.member.service;

import com.example.sogong.domain.member.repository.MemberRepository;
import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.review.dto.response.ReviewResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.example.sogong.domain.member.dto.request.MemberRequestDto;
import com.example.sogong.domain.member.dto.response.MemberResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService
{
    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto getMember(Long memberId)
    {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        return new MemberResponseDto(member);
    }

    @Transactional
    public MemberResponseDto updateMember(Long memberId, MemberRequestDto memberRequestDto)
    {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        member.update(memberRequestDto);
        return new MemberResponseDto(member);
    }

    @Transactional
    public void deleteMember(Long memberId)
    {
        memberRepository.deleteById(memberId);
    }

    @Transactional
    public ResponseEntity<Page<OrderResponseDto>> getPurchasedProducts(Long memberId, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        return memberRepository.findOrdersById(memberId, pageable).map(OrderResponseDto::new);
    }

    @Transactional
    public ResponseEntity<Page<ReviewResponseDto>> getWrittenReviews(Long memberId, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        return memberRepository.findReviewsById(memberId, pageable).map(ReviewResponseDto::new);
    }
}





















