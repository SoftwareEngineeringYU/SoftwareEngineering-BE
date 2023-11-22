package com.example.sogong.domain.product.dto.response;

import com.example.sogong.domain.product.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductResponseDto {
    private String name;
    private Integer price;
    private String description;
    private Integer stockAmount;
    private URL imageUrl;

    public ProductResponseDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.stockAmount = product.getStockAmount();
    }
}
