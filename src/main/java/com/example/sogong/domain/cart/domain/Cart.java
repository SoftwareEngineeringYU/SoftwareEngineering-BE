package com.example.sogong.domain.cart.domain;

import com.example.sogong.domain.cart_product.domain.CartProduct;
import com.example.sogong.domain.common.BaseTimeEntity;
import com.example.sogong.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Cart extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "OWNER_ID", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Member owner;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProduct> cartItems;

    @Builder
    private Cart(Member owner) {
        this.owner = owner;
        this.cartItems = new ArrayList<>();
    }

}
