package com.example.sogong.domain.cart_product.dto.response;

import com.example.sogong.domain.cart.domain.Cart;
import com.example.sogong.domain.cart_product.domain.CartProduct;
import com.example.sogong.domain.product.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CartProductResponseDto {
    private Integer quantity;
    private Product product;
    private Cart cart;

    public CartProductResponseDto(CartProduct cartProduct) {
        this.quantity = cartProduct.getQuantity();
        this.product = cartProduct.getProduct();
        this.cart = cartProduct.getCart();
    }
}
