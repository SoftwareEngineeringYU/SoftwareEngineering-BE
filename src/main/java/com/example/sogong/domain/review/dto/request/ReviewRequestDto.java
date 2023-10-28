package com.example.sogong.domain.review.dto.request;

import com.example.sogong.domain.image.domain.ImageData;
import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.product.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewRequestDto {
    private String title;
    private String body;
    private Integer rating;
    private Member writer;
    private Product product;
    private List<ImageData> images;
}
