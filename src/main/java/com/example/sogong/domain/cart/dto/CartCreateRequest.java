package com.example.sogong.domain.cart.dto;

import com.example.sogong.domain.cart.domain.Cart;
import com.example.sogong.domain.cart_product.domain.CartProduct;
import com.example.sogong.domain.member.domain.Member;

import java.util.List;

public record CartCreateRequest(Member owner, List<CartProduct> cartItems)
{
    public Cart toEntity()
    {
        return Cart.builder()
                .owner(owner)
           //     .cartItems(cartItems)
                .build();
    }
}
