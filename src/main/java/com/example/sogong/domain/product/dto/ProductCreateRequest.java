package com.example.sogong.domain.product.dto;

public record ProductCreateRequest(String name, String description, Integer price, Integer stockQuantity) {

}
