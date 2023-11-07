package com.example.sogong.domain.order.service;

import com.example.sogong.domain.address.repository.AddressRepository;
import com.example.sogong.domain.member.repository.MemberRepository;
import com.example.sogong.domain.order.domain.CsStatus;
import com.example.sogong.domain.order.domain.Order;
import com.example.sogong.domain.order.domain.OrderStatus;
import com.example.sogong.domain.order.dto.request.OrderRequestDto;
import com.example.sogong.domain.order.dto.response.OrderResponseDto;
import com.example.sogong.domain.order.repository.OrderRepository;
import com.example.sogong.global.common.response.code.ErrorCode;
import com.example.sogong.global.common.response.exception.GlobalErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        final Order order = Order.builder()
                .buyer(memberRepository.getReferenceById(orderRequestDto.buyerId()))
                .orderStatus(OrderStatus.AWAIT_PAYMENT)
                .purchasedAt(Instant.now())
                .shippingAddress(addressRepository.getReferenceById(orderRequestDto.addressId()))
                .csStatus(CsStatus.OK)
                .build();

        orderRepository.save(order);
        return OrderResponseDto.from(order);
    }

    public OrderResponseDto getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new GlobalErrorException(ErrorCode.NOT_FOUND_ERROR));

        return OrderResponseDto.from(order);
    }

    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
