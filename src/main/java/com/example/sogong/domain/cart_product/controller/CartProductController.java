package com.example.sogong.domain.cart_product.controller;


import com.example.sogong.domain.cart_product.dto.request.CartProductRequestDto;
import com.example.sogong.domain.cart_product.service.CartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CartProductController {
    private final CartProductService cartProductService;

    // 카트 상품 등록
    @PostMapping("")
    public ResponseEntity<?> createCartProduct(
            @RequestBody CartProductRequestDto cartProductRequestDto) {

        return new ResponseEntity<>(cartProductService.createCartProduct(cartProductRequestDto), HttpStatus.OK);
    }

    // 카트 상품 반환
    @GetMapping("/{id}")
    public ResponseEntity<?> getCartProduct(@PathVariable Long id) {
        return new ResponseEntity<>(cartProductService.getCartProduct(id), HttpStatus.OK);
    }

    // 카트 상품 수정
    @PutMapping()
    public ResponseEntity<?> updateCartProduct(
            @PathVariable Long cartProductId, CartProductRequestDto cartProductRequestDto) {
        return new ResponseEntity<>(cartProductService.updateCartProduct(cartProductId, cartProductRequestDto), HttpStatus.OK);
    }

    // 카트 상품 삭제
    @DeleteMapping()
    public void deleteCartProduct(@PathVariable Long cartProductId) {
        cartProductService.deleteCartProduct(cartProductId);
    }

}
