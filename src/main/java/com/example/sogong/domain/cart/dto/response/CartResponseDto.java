package com.example.sogong.domain.cart.dto.response;

import com.example.sogong.domain.cart.domain.Cart;
import com.example.sogong.domain.cart_product.domain.CartProduct;
import com.example.sogong.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CartResponseDto {
    private Member owner;
    private List<CartProduct> cartItems;

    public CartResponseDto(Cart cart) {
        this.owner = cart.getOwner();
        this.cartItems = cart.getCartItems();
    }
}
