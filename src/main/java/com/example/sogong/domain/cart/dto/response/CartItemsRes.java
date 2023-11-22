package com.example.sogong.domain.cart.dto.response;

import com.example.sogong.domain.product.domain.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Schema(description = "장바구니 상품 리스트 조회 응답")
public class CartItemsRes {
    @Schema(description = "장바구니 상품 리스트")
    private List<CartItem> products;
    @Schema(description = "장바구니 총 가격")
    private Integer totalPrice;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CartItem {
        @Schema(description = "상품 ID")
        private Long productId;
        @Schema(description = "상품 이름")
        private String productName;
        @Schema(description = "상품 이미지")
        private String productImage;
        @Schema(description = "상품 가격")
        private Integer productPrice;
        @Schema(description = "상품 수량")
        private Integer quantity;
        @Schema(description = "개별 상품 총 가격")
        private Integer subtotalPrice;

        @Builder
        private CartItem(Long productId, String productName, String productImage, Integer productPrice, Integer quantity, Integer subtotalPrice) {
            this.productId = productId;
            this.productName = productName;
            this.productImage = productImage;
            this.productPrice = productPrice;
            this.quantity = quantity;
            this.subtotalPrice = subtotalPrice;
        }

        public static CartItem fromEntity(Product product, Integer quantity) {
            return CartItem.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .productImage(product.getImages().get(0).getThumbnailImageKey())
                    .productPrice(product.getPrice())
                    .quantity(quantity)
                    .subtotalPrice(product.getPrice() * quantity)
                    .build();
        }
    }

    private CartItemsRes(List<CartItem> products, Integer totalPrice) {
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public static CartItemsRes of(List<CartItem> products, Integer totalPrice) {
        return new CartItemsRes(products, totalPrice);
    }
}
