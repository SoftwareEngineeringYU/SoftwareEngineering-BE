package com.example.sogong.domain.cart_product.domain;

import com.example.sogong.domain.cart.domain.Cart;
import com.example.sogong.domain.common.BaseTimeEntity;
import com.example.sogong.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CartProduct extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @JoinColumn(name = "CART_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;


    @Builder
    private CartProduct(Integer quantity, Product product, Cart cart) {
        this.quantity = quantity;
        this.product = product;
        this.cart = cart;
    }

}
