package com.example.sogong.domain.review.api;

import com.example.sogong.domain.review.dto.request.ReviewRequestDto;
import com.example.sogong.domain.review.service.ReviewService;
import com.example.sogong.global.common.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping
    public ResponseEntity<?> createReview(
            @RequestBody ReviewRequestDto reviewRequestDto) {
        return new ResponseEntity<>(reviewService.createReview(reviewRequestDto), HttpStatus.OK);
    }

    // 리뷰 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getReview(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.getReview(id), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<?> getReviewsByProducts(@PathVariable Long productId, Pageable pageable) {
        return ResponseEntity.ok(SuccessResponse.from(reviewService.getReviewsByProductId(productId, pageable)));
    }

    // 리뷰 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewRequestDto reviewRequestDto) {
        return new ResponseEntity<>(reviewService.updateReview(id, reviewRequestDto), HttpStatus.OK);
    }

    // 리뷰 삭제
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }

}
