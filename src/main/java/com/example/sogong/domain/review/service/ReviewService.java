package com.example.sogong.domain.review.service;

import com.example.sogong.domain.member.repository.MemberRepository;
import com.example.sogong.domain.product.repository.ProductRepository;
import com.example.sogong.domain.review.domain.Review;
import com.example.sogong.domain.review.dto.request.ReviewRequestDto;
import com.example.sogong.domain.review.dto.response.ReviewResponseDto;
import com.example.sogong.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ReviewResponseDto createReview(ReviewRequestDto reviewRequestDto) {
        return ReviewResponseDto.from(reviewRepository.save(new Review(
                reviewRequestDto,
                memberRepository.getReferenceById(reviewRequestDto.getWriterId()),
                productRepository.getReferenceById(reviewRequestDto.getProductId())
        )));
    }

    @Transactional
    public ReviewResponseDto getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        return ReviewResponseDto.from(review);
    }

    public List<ReviewResponseDto> getReviewsByProductId(Long productId, Pageable pageable) {
        return reviewRepository.findAllByProductId(productId, pageable).stream()
                .map(ReviewResponseDto::from)
                .toList();
    }

    @Transactional
    public ReviewResponseDto updateReview(Long reviewId, ReviewRequestDto reviewRequestDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        review.update(reviewRequestDto);
        return ReviewResponseDto.from(review);
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
