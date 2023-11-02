package com.example.sogong.domain.order.dto.request;

import java.util.List;

public record OrderRequestDto(
        Long addressId,
        Long buyerId,
        List<Long> orderProductIds
) {
}
