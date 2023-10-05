package com.example.sogong.domain.order.domain;

import com.example.sogong.domain.address.domain.Address;
import com.example.sogong.domain.common.BaseTimeEntity;
import com.example.sogong.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "orders")
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Instant purchasedAt; // 주문일자

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 주문 상태

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CsStatus csStatus; // CS 상태

    @Embedded
    private Address shippingAddress; // 배송지

    private String address;
    private String zipcode;

    @JoinColumn(name = "BUYER_ID", nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member buyer; // 구매자


    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts; // 구매한 상품들


    @Builder
    private Order(Instant purchasedAt, OrderStatus orderStatus, CsStatus csStatus, Address shippingAddress, Member buyer) {
        this.purchasedAt = purchasedAt;
        this.orderStatus = orderStatus;
        this.csStatus = csStatus;
        this.shippingAddress = shippingAddress;
        this.buyer = buyer;
        this.orderProducts = new ArrayList<>();
    }

}
