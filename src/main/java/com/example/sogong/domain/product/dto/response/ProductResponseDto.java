package com.example.sogong.domain.product.dto.response;

import com.example.sogong.domain.product.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductResponseDto {

    private long id;
    private String name;
    private Integer price;
    private String description;
    private Integer stockAmount;
    private String imageUrl;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.stockAmount = product.getStockAmount();
        this.imageUrl = product.getImages().get(0).getImageUrl();
    }
}
