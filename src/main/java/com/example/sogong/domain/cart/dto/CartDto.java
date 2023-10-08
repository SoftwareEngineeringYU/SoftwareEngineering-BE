package com.example.sogong.domain.cart.dto;

import com.example.sogong.domain.cart.domain.Cart;
import com.example.sogong.domain.cart_product.domain.CartProduct;
import com.example.sogong.domain.member.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class CartDto
{
    private Member owner;

    private List<CartProduct> cartItems;

    public CartDto(Cart cart)
    {
        this.owner=cart.getOwner();
        this.cartItems=cart.getCartItems();

    }
}
