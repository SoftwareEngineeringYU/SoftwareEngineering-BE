package com.example.sogong.domain.payment.domain;

import com.example.sogong.domain.order.domain.Order;
import com.example.sogong.domain.payment.dto.request.PaymentRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Order order;

    public Payment(PaymentRequestDto paymentRequestDto) {
        this.order = paymentRequestDto.getOrder();
    }

    public void update(PaymentRequestDto paymentRequestDto) {
        this.order = paymentRequestDto.getOrder();
    }

}
