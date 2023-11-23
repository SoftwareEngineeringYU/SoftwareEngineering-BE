package com.example.sogong.domain.image.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ImageData {
    String imageUrl;

    public String getImageUrl() {
        return "https://" + imageUrl;
    }
}
