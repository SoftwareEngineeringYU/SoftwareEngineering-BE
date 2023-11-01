package com.example.sogong.domain.cart.controller;

import com.example.sogong.domain.cart.dto.request.CartRequestDto;
import com.example.sogong.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    // 카트 생성
    @PostMapping
    public ResponseEntity<?> createCart(@RequestBody CartRequestDto cartRequestDto) {
        return new ResponseEntity<>(cartService.createCart(cartRequestDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCart(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.getCart(id), HttpStatus.OK);
    }

    // 카트 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCart(
            @PathVariable Long id,
            @RequestBody CartRequestDto cartRequestDto) {
        return new ResponseEntity<>(cartService.updateCart(id, cartRequestDto), HttpStatus.OK);
    }

    // 카트 삭제
    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }
}
