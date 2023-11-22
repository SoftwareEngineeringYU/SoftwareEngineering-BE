package com.example.sogong.domain.cart.controller;

import com.example.sogong.domain.cart.domain.Cart;
import com.example.sogong.domain.cart.dto.request.CartRequestDto;
import com.example.sogong.domain.cart.dto.response.CartItemsRes;
import com.example.sogong.domain.cart.service.CartService;
import com.example.sogong.global.common.response.SuccessResponse;
import com.example.sogong.global.resolver.access_token.AccessToken;
import com.example.sogong.global.resolver.access_token.AccessTokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartService cartService;

    @GetMapping("")
    public ResponseEntity<?> getCart(@AccessTokenInfo AccessToken token) {
        CartItemsRes cart = cartService.findCartItems(token.subject());
        return ResponseEntity.ok(SuccessResponse.from(cart));
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
