package com.example.sogong.domain.cart_product.dto.request;

import com.example.sogong.domain.cart.domain.Cart;
import com.example.sogong.domain.cart_product.domain.CartProduct;
import com.example.sogong.domain.product.domain.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CartProductRequestDto
{
    private Integer quantity;
    private Product product;
    private Cart cart;
}
