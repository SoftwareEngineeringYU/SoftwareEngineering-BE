package com.example.sogong.domain.order.api;

import com.example.sogong.domain.order.dto.request.OrderRequestDto;
import com.example.sogong.domain.order.service.OrderService;
import com.example.sogong.global.common.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    // 주문 생성
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        var data = orderService.createOrder(orderRequestDto);
        return ResponseEntity.ok(SuccessResponse.from(data));
    }

    // 주문 반환
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        var data = orderService.getOrder(id);
        return ResponseEntity.ok(SuccessResponse.from(data));
    }

    // 주문 삭제
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

}
