package com.example.sogong.domain.payment.domain;

import com.example.sogong.domain.common.BaseTimeEntity;
import com.example.sogong.domain.order.domain.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private String mid;


    public Payment(Order order, String uid, String mid) {
        this.order = order;
        this.uid = uid;
        this.mid = mid;
    }

}
