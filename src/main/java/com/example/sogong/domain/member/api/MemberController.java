package com.example.sogong.domain.member.api;

import com.example.sogong.domain.member.dto.request.MemberRequestDto;
import com.example.sogong.domain.member.dto.response.MemberResponseDto;
import com.example.sogong.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long id) {
        //ToDO get memberId

        return new ResponseEntity<>(memberService.getMember(id), HttpStatus.OK);
    }

    //수정
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(
            @PathVariable Long id,
            @RequestBody MemberRequestDto memberRequestDto) {
        //ToDO get memberId

        return new ResponseEntity<>(memberService.updateMember(id, memberRequestDto), HttpStatus.OK);
    }

    //삭제
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        //ToDO get memberId
        memberService.deleteMember(id);
    }

//    // 구매한 상품 반환
//    @GetMapping("/orders")
//    public ResponseEntity<Page<OrderResponseDto>> getPurchasedProducts(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "createdAt,asc") String sort) {
//
//        //ToDO get memberId
//
//        return memberService.getPurchasedProducts(memberId, page, size, sort);
//    }
//
//    // 작성한 리뷰 반환
//    @GetMapping("/reviews")
//    public ResponseEntity<Page<ReviewResponseDto>> getWrittenReviews(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "createdAt,asc") String sort) {
//
//        //ToDO get memberId
//
//        return memberService.getWrittenReviews(memberId, page, size, sort);
//    }
}