package com.example.sogong.domain.product.dto;

import com.example.sogong.domain.product.domain.Product;

public record CreationProductReq(String name, String description, Integer price, Integer stockQuantity) {
    public Product toEntity() {
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .stockAmount(stockQuantity)
                .build();
    }
}
