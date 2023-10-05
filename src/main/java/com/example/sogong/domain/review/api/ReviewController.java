package com.example.sogong.domain.review.api;

import com.example.sogong.domain.review.application.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    //조회
    @GetMapping("/{reviewId}")
    public ResponseEntity<> getMember(@PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);
    }

    //수정
    @PutMapping("/{reviewId}")
    public ResponseEntity<> updateMember(@PathVariable Long reviewId ,  ) {
        return new ResponseEntity<>(reviewService.updateReview(reviewId,), HttpStatus.OK);
    }

    //삭제
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }

}
