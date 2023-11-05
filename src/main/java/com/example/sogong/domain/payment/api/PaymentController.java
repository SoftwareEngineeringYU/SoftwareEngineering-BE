package com.example.sogong.domain.payment.api;

import com.example.sogong.domain.payment.dto.request.PaymentRequestDto;
import com.example.sogong.domain.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    // 지불 생성
    @PostMapping
    public ResponseEntity<?> createPayment(PaymentRequestDto paymentRequestDto) {
        return new ResponseEntity<>(paymentService.createPayment(paymentRequestDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPayment(@PathVariable Long id) {
        return new ResponseEntity<>(paymentService.getPayment(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePayment(
            @PathVariable Long id,
            @RequestBody PaymentRequestDto paymentRequestDto) {
        return new ResponseEntity<>(paymentService.updatePayment(id, paymentRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("{/id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
}
