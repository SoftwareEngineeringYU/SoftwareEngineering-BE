package com.example.sogong.domain.cart_product.controller;


import com.example.sogong.domain.cart_product.dto.CartProductCreateRequest;
import com.example.sogong.domain.cart_product.dto.CartProductDto;
import com.example.sogong.domain.cart_product.service.CartProductService;
import com.example.sogong.domain.product.dto.CreationProductReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CartProductController
{
    private final CartProductService cartProductService;

    //조회는 카트에서? or 멤버?

    //등록..
    @PostMapping("")
    public ResponseEntity<?> createCartProduct(@RequestBody CartProductCreateRequest cartProductReq) {
        cartProductService.createCartProduct(cartProductReq);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(successWithNoContent());
    }

    //수정
    @PutMapping()
    public ResponseEntity<CartProductDto> updateCartProduct(@PathVariable Long cartProductId, CartProductDto cartProductDto)
    {
        return new ResponseEntity<>(cartProductService.updateCartProduct(cartProductId,cartProductDto), HttpStatus.OK);
    }

    //삭제
    @DeleteMapping()
    public void deleteCartProduct(@PathVariable Long cartProductId)
    {
        cartProductService.deleteCartProduct(cartProductId);
    }

}
