package com.example.sogong.domain.image.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class ImageData {
    String thumbnailImageKey;
    String imageKey;
}
