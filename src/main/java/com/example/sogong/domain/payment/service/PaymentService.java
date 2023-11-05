package com.example.sogong.domain.payment.service;

import com.example.sogong.domain.payment.domain.Payment;
import com.example.sogong.domain.payment.dto.request.PaymentRequestDto;
import com.example.sogong.domain.payment.dto.response.PaymentResponseDto;
import com.example.sogong.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Transactional
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        return new PaymentResponseDto(paymentRepository
                .save(new Payment(paymentRequestDto)));
    }

    @Transactional
    public PaymentResponseDto updatePayment(Long id, PaymentRequestDto paymentRequestDto) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(""));

        payment.update(paymentRequestDto);

        return new PaymentResponseDto(payment);
    }

    @Transactional
    public PaymentResponseDto getPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(""));

        return new PaymentResponseDto(payment);
    }

    @Transactional
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}