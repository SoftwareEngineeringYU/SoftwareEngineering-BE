package com.example.sogong.domain.cart_product.dto;

import com.example.sogong.domain.cart.domain.Cart;
import com.example.sogong.domain.cart_product.domain.CartProduct;
import com.example.sogong.domain.product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CartProductDto
{

    private Integer quantity;
    private Product product;
    private Cart cart;

    public CartProductDto(CartProduct cartProduct) {
        this.quantity = cartProduct.getQuantity();
        this.product = cartProduct.getProduct();
        this.cart = cartProduct.getCart();
    }
}
