package com.example.sogong.domain.review.dto.response;

import com.example.sogong.domain.image.domain.ImageData;
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
    private int rating;
    private String writerName;
    private long productId;
    private List<ImageData> images;

    public static ReviewResponseDto from(Review review) {
        return new ReviewResponseDto(
                review.getTitle(),
                review.getBody(),
                review.getRating(),
                review.getWriter().getNickname(),
                review.getProduct().getId(),
                review.getImages()
        );
    }

    private ReviewResponseDto(String title, String body, int rating, String writerName, long productId, List<ImageData> images) {
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.writerName = writerName;
        this.productId = productId;
        this.images = images;
    }

}
