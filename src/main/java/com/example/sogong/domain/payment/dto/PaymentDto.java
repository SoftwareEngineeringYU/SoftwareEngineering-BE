package com.example.sogong.domain.payment.dto;

import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.payment.domain.Payment;

public record PaymentDto(
        String pg,
        String payMethod,
        String merchantUid,
        String productName,
        Integer amount,
        String buyerEmail,
        String buyerName,
        String buyerTel,
        String buyerAddr,
        String buyerPostCode
) {

    public static PaymentDto from(Payment payment) {
        Member buyer = payment.getOrder().getBuyer();

        return new PaymentDto(
                "kcp",
                "card",
                "M_" + payment.getId(),
                payment.getOrder().getOrderName(),
                payment.getOrder().getTotalPrice(),
                buyer.getEmail(),
                buyer.getNickname(),
                "010-1234",
                payment.getOrder().getShippingAddress().getAddress(),
                payment.getOrder().getShippingAddress().getZipcode()
        );
    }
}
