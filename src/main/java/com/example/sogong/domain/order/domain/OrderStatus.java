package com.example.sogong.domain.order.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {

    AWAIT_PAYMENT("결제 대기중"),
    PREPARING("상품 준비중"),
    DELIVERING("상품 배송중"),
    DELIVERING_SUSPENDED("배송 보류"),
    DELIVERY_COMPLETED("배송 완료"),

    ;

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }
}
