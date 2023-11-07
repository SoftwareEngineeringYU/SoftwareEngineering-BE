package com.example.sogong.domain.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PortOnePaymentResult(
        @JsonProperty("imp_uid")
        String impUid,
        @JsonProperty("merchant_uid")
        String merchantUid,

        @JsonProperty("paid_amount")
        int paidAmount,
        @JsonProperty("pay_method")
        String payMethod,
        @JsonProperty("pg_provider")
        String pgProvider,
        @JsonProperty("status")
        String status,
        boolean success
) {
}
