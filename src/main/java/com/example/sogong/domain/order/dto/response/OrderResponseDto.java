package com.example.sogong.domain.order.dto.response;

import com.example.sogong.domain.address.domain.Address;
import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.order.domain.CsStatus;
import com.example.sogong.domain.order.domain.Order;
import com.example.sogong.domain.order.domain.OrderProduct;
import com.example.sogong.domain.order.domain.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderResponseDto {
    private Instant purchasedAt;
    private OrderStatus orderStatus;
    private CsStatus csStatus;
    private Address shippingAddress;
    private Member buyer;
    private List<OrderProduct> orderProducts;

    public static OrderResponseDto from(Order order) {
        return new OrderResponseDto(order);
    }

    private OrderResponseDto(Order order) {
        this.purchasedAt = order.getPurchasedAt();
        this.orderStatus = order.getOrderStatus();
        this.csStatus = order.getCsStatus();
        this.shippingAddress = order.getShippingAddress();
        this.buyer = order.getBuyer();
        this.orderProducts = order.getOrderProducts();
    }
}
