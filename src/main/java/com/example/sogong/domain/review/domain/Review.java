package com.example.sogong.domain.review.domain;

import com.example.sogong.domain.common.BaseTimeEntity;
import com.example.sogong.domain.image.domain.ImageData;
import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.product.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder
    private Review(String title, String body, Integer rating, Member writer, Product product, List<ImageData> images) {
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.writer = writer;
        this.product = product;
        this.images = images;
    }

}
