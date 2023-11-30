package com.example.sogong.domain.review.domain;

import com.example.sogong.domain.common.BaseTimeEntity;
import com.example.sogong.domain.image.domain.ImageData;
import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.product.domain.Product;
import com.example.sogong.domain.review.dto.request.ReviewRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "리뷰 제목은 공백이 아니어야 합니다")
    private String title;

    @NotBlank(message = "리뷰는 공백이 아니어야 합니다")
    @Size(min = 10, max = 500, message = "리뷰는 10 ~ 500자 사이 이내여야 합니다")
    private String body;

    private Integer rating;

    @JoinColumn(name = "WRITER_ID", nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    @JoinColumn(name = "PRODUCT_ID", nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "review_image",
            joinColumns = @JoinColumn(name = "REVIEW_ID")
    )
    @OrderColumn
    private List<ImageData> images;

    public Review(ReviewRequestDto reviewRequestDto, Member writer, Product product) {
        this.title = reviewRequestDto.getTitle();
        this.body = reviewRequestDto.getBody();
        this.rating = reviewRequestDto.getRating();
        this.writer = writer;
        this.product = product;
        this.images = reviewRequestDto.getImages();
    }

    public void update(ReviewRequestDto reviewRequestDto) {
        this.title = reviewRequestDto.getTitle();
        this.body = reviewRequestDto.getBody();
        this.rating = reviewRequestDto.getRating();
        this.images = reviewRequestDto.getImages();
    }

}
