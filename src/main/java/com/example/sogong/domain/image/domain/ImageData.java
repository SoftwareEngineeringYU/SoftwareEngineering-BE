package com.example.sogong.domain.image.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
public class ImageData {
    String thumbnailImageKey;
    String imageKey;

    public ImageData(String thumbnailImageKey, String imageKey) {
        this.thumbnailImageKey = thumbnailImageKey;
        this.imageKey = imageKey;
    }
}
