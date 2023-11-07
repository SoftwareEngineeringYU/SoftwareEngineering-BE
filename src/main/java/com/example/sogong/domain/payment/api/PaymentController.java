package com.example.sogong.domain.payment.api;

import com.example.sogong.domain.payment.dto.PortOnePaymentResult;
import com.example.sogong.domain.payment.service.PaymentService;
import com.example.sogong.global.common.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/complete")
    public ResponseEntity<?> handlePaymentComplete(
            @RequestParam("order") Long orderId,
            @Valid @RequestBody PortOnePaymentResult portOnePaymentResult
    ) {
        var data = paymentService.verify(orderId, portOnePaymentResult);
        return ResponseEntity.ok(SuccessResponse.from(data));
    }

    @PostMapping("/error")
    public ResponseEntity<?> handlePaymentError(
            @Valid @RequestBody PortOnePaymentResult portOnePaymentResult
    ) {
        return ResponseEntity.unprocessableEntity().build();
    }


    @GetMapping("/{paymentId}")
    public ResponseEntity<?> getPayment(@PathVariable Long paymentId) {
        var data = paymentService.getPayment(paymentId);
        return ResponseEntity.ok(SuccessResponse.from(data));
    }

}
