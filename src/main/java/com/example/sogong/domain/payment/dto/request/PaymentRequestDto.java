package com.example.sogong.domain.payment.dto.request;

import com.example.sogong.domain.order.domain.Order;
import lombok.Getter;

@Getter
public class PaymentRequestDto {
    Order order;
}
