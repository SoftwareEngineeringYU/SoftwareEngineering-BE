package com.example.sogong.domain.order.domain;

import lombok.Getter;

@Getter
public enum CsStatus {

    OK("해당없음"),
    CANCEL("취소"),
    RETURN("반품"),
    EXCHANGE("교환"),
    REFUND("환불"),

    ;

    private final String description;

    CsStatus(String description) {
        this.description = description;
    }
}
