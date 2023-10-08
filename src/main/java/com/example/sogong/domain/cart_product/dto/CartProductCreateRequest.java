package com.example.sogong.domain.cart_product.dto;

import com.example.sogong.domain.cart.domain.Cart;
import com.example.sogong.domain.cart_product.domain.CartProduct;
import com.example.sogong.domain.product.domain.Product;

public record CartProductCreateRequest(Integer quantity, Product product, Cart cart)
{
    public CartProduct toEntity()
    {
        return CartProduct.builder()
                .quantity(quantity)
                .product(product)
                .cart(cart)
                .build();
    }
}
