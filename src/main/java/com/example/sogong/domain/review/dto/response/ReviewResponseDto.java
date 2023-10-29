package com.example.sogong.domain.review.dto.response;

import com.example.sogong.domain.image.domain.ImageData;
import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.product.domain.Product;
import com.example.sogong.domain.review.domain.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewResponseDto {
    private String title;
    private String body;
    private Integer rating;
    private Member writer;
    private Product product;
    private List<ImageData> images;

    public ReviewResponseDto(Review review) {
        this.title = review.getTitle();
        this.body = review.getBody();
        this.rating = review.getRating();
        this.writer = review.getWriter();
        this.product = review.getProduct();
        this.images = review.getImages();
    }
}
