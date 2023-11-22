package com.example.sogong.domain.cart.domain;

import com.example.sogong.domain.common.BaseTimeEntity;
import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Cart extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @JoinColumn(name = "OWNER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member owner;

    @JoinColumn(name = "PRODUCT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private Cart(Member owner, Product product) {
        this.owner = owner;
        this.product = product;
    }

    public static Cart of(Member owner, Product product) {
        return new Cart(owner, product);
    }

    public void plusQuantity() {
        ++this.quantity;
    }

    public void minusQuantity() {
        --this.quantity;
    }

    public void updateOwner(Member owner) {
        if (this.owner != null) {
            owner.getCarts().remove(this);
        }

        this.owner = owner;

        if (owner != null) {
            owner.getCarts().add(this);
        }
    }
}
