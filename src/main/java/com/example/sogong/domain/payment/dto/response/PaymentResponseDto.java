package com.example.sogong.domain.payment.dto.response;

import com.example.sogong.domain.order.domain.Order;
import com.example.sogong.domain.payment.domain.Payment;
import lombok.Getter;

@Getter
public class PaymentResponseDto {
    Order order;

    public PaymentResponseDto(Payment payment) {
        this.order = payment.getOrder();
    }
}
