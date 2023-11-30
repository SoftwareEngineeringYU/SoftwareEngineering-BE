package com.example.sogong.domain.review.dto.request;

import com.example.sogong.domain.image.domain.ImageData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewRequestDto {
    private String title;
    private String body;
    private int rating;
    private long writerId;
    private long productId;
    private List<ImageData> images;
}
