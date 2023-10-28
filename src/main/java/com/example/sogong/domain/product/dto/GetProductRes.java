package com.example.sogong.domain.product.dto;

import com.example.sogong.domain.product.domain.Product;
import lombok.Builder;

@Builder
public record GetProductRes(Long id, String name, String description, Integer price, Integer stockQuantity) {
    public static GetProductRes of(Product product) {
        return GetProductRes.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockAmount())
                .build();
    }
}
