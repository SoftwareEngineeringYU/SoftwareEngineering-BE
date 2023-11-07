package com.example.sogong.domain.payment.service;

import com.example.sogong.domain.order.domain.Order;
import com.example.sogong.domain.order.repository.OrderRepository;
import com.example.sogong.domain.payment.domain.Payment;
import com.example.sogong.domain.payment.dto.PaymentDto;
import com.example.sogong.domain.payment.dto.PortOnePaymentResult;
import com.example.sogong.domain.payment.repository.PaymentRepository;
import com.example.sogong.global.common.response.code.ErrorCode;
import com.example.sogong.global.common.response.exception.GlobalErrorException;
import com.example.sogong.infra.payment.PayClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PayClient payClient;

    public PaymentDto getPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(""));

        return PaymentDto.from(payment);
    }

    @Transactional
    public PaymentDto verify(Long orderId, PortOnePaymentResult paymentResult) {
        log.info("IMP UID: {}", paymentResult.impUid());

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new GlobalErrorException(ErrorCode.NOT_FOUND_ERROR));

        payClient.verify(
                paymentResult.impUid(),
                paymentResult.paidAmount(),
                order.getTotalPrice());

        Payment payment = new Payment(order, paymentResult.impUid(), paymentResult.merchantUid());
        paymentRepository.save(payment);
        log.info("결제 정보 저장: {}, {}", payment.getId(), payment.getUid());
        order.completePayment();

        return PaymentDto.from(payment);
    }

}
