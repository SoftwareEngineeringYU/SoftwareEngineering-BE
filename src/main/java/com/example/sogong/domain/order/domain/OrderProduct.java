package com.example.sogong.domain.order.domain;

import com.example.sogong.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString(exclude = {"order", "product"})
@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int price; // 구매한 가격
    private int quantity; // 구매한 수량

    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @JoinColumn(name = "ORDER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Builder
    private OrderProduct(int price, int quantity, Product product) {
        this.price = price;
        this.quantity = quantity;
        this.product = product;
    }

}
