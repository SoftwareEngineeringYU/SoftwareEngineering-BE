package com.example.sogong.domain.cart.controller;

import com.example.sogong.domain.cart.domain.Cart;
import com.example.sogong.domain.cart.dto.request.CartRequestDto;
import com.example.sogong.domain.cart.dto.response.CartItemsRes;
import com.example.sogong.domain.cart.service.CartService;
import com.example.sogong.global.common.response.SuccessResponse;
import com.example.sogong.global.resolver.access_token.AccessToken;
import com.example.sogong.global.resolver.access_token.AccessTokenInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "장바구니", description = "장바구니 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartService cartService;

    @Operation(summary = "장바구니 리스트 조회")
    @GetMapping("")
    public ResponseEntity<?> getCart(@AccessTokenInfo AccessToken token) {
        CartItemsRes cart = cartService.findCartItems(token.subject());
        return ResponseEntity.ok(SuccessResponse.from(cart));
    }

    @Operation(summary = "장바구니 상품 추가")
    @Parameters({
            @Parameter(name = "product_id", description = "상품 아이디"),
            @Parameter(name = "quantity", description = "상품 수량", example = "1", required = false)
    })
    @PostMapping("")
    public ResponseEntity<?> addCart(
            @AccessTokenInfo AccessToken token,
            @RequestParam("product_id") Long productId,
            @RequestParam(value = "quantity", defaultValue = "1", required = false) Integer quantity) {
        cartService.addCart(token.subject(), productId, quantity);
        return ResponseEntity.ok(SuccessResponse.noContent());
    }

    @Operation(summary = "장바구니 상품 수량 변경")
    @Parameter(name = "diff", description = "plus or minus", example = "plus")
    @GetMapping("/{cart_id}")
    public ResponseEntity<?> updateCart(@PathVariable("cart_id") Long cartId, @RequestParam("diff") String diff) {
        cartService.updateCart(cartId, diff);
        return ResponseEntity.ok(SuccessResponse.noContent());
    }

    @Operation(summary = "장바구니 상품 삭제")
    @DeleteMapping("/{cart_id}")
    public void deleteCart(@PathVariable Long cart_id) {
        cartService.deleteCart(cart_id);
    }
}
