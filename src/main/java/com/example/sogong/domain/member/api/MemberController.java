package com.example.sogong.domain.member.api;

import com.example.sogong.domain.member.service.MemberService;
import com.example.sogong.domain.member.dto.response.MemberResponseDto;

import com.example.sogong.domain.review.dto.response.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/members")
public class MemberController
{
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<MemberResponseDto> getMember() {
        //ToDO get memberId

        return new ResponseEntity<>(memberService.getMember(memberId), HttpStatus.OK);
    }

    //수정
    @PutMapping
    public ResponseEntity<MemberResponseDto> updateMember() {
        //ToDO get memberId

        return new ResponseEntity<>(memberService.updateMember(memberId, memberRequestDto), HttpStatus.OK);
    }

    //삭제
    @DeleteMapping
    public void deleteMember() {
        //ToDO get memberId
        memberService.deleteMember(memberId);
    }

    // 구매한 상품 반환
    @GetMapping("/orders")
    public ResponseEntity<Page<OrderResponseDto>> getPurchasedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,asc") String sort) {

        //ToDO get memberId

        return memberService.getPurchasedProducts(memberId, page, size, sort);
    }

    // 작성한 리뷰 반환
    @GetMapping("/reviews")
    public ResponseEntity<Page<ReviewResponseDto>> getWrittenReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,asc") String sort) {

        //ToDO get memberId

        return memberService.getWrittenReviews(memberId, page, size, sort);
    }
}