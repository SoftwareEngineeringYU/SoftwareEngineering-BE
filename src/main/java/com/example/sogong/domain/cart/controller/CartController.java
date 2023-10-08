package com.example.sogong.domain.cart.controller;

import com.example.sogong.domain.address.dto.AddressCreateRequest;
import com.example.sogong.domain.address.dto.AddressDto;
import com.example.sogong.domain.cart.dto.CartCreateRequest;
import com.example.sogong.domain.cart.dto.CartDto;
import com.example.sogong.domain.cart.service.CartService;
import com.example.sogong.domain.cart_product.service.CartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CartController
{
    private final CartService cartService;

    //물품들 보기??


    //생성
    @PostMapping
    public ResponseEntity<?> createCart(@RequestBody CartCreateRequest cartReq) {
        cartService.createCart(cartReq);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(successWithNoContent());
    }


    //수정
    @PutMapping
    public ResponseEntity<CartDto> updateCart(@PathVariable Long cartId, CartDto cartDto)
    {
        return new ResponseEntity<>(cartService.updateCart(cartId,cartDto), HttpStatus.OK);
    }

    //삭제
    @DeleteMapping
    public void deleteCart(@PathVariable Long cartId)
    {
        cartService.deleteCart(cartId);
    }
}
