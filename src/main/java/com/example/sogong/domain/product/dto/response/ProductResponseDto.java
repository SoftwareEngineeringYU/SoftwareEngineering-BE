package com.example.sogong.domain.product.dto.response;

import com.example.sogong.domain.category.domain.Category;
import com.example.sogong.domain.image.domain.ImageData;
import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.product.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductResponseDto {
    private String name;
    private Integer price;
    private String description;
    private Integer stockAmount;
    private Category category;
    private Member seller;
    private List<ImageData> images;


    public ProductResponseDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.stockAmount = product.getStockAmount();
        this.category = product.getCategory();
        this.seller = product.getSeller();
        this.images = product.getImages();
    }
}
