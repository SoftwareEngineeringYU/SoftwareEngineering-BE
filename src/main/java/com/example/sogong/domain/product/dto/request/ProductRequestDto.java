package com.example.sogong.domain.product.dto.request;

import com.example.sogong.domain.category.domain.Category;
import com.example.sogong.domain.image.domain.ImageData;
import com.example.sogong.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductRequestDto {
    private String name;
    private Integer price;
    private String description;
    private Integer stockAmount;
    private Category category;
    private Member seller;
    private List<ImageData> images;
}
