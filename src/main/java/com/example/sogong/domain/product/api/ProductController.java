package com.example.sogong.domain.product.api;

import com.example.sogong.domain.product.dto.request.ProductRequestDto;
import com.example.sogong.domain.product.service.ProductRecommendService;
import com.example.sogong.domain.product.service.ProductService;
import com.example.sogong.global.common.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductRecommendService productRecommendService;

    // 상품 생성
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(productService.createProduct(productRequestDto), HttpStatus.OK);
    }

    // 상품 전체 반환
    @GetMapping
    public ResponseEntity<?> getAllProduct(Pageable pageable) {
        final var data = productService.getAllProduct(pageable);
        return ResponseEntity.ok(SuccessResponse.from(data));
    }

    @GetMapping("/name")
    public ResponseEntity<?> getProductByName(@RequestParam String name, Pageable pageable) {
        final var data = productService.findProductsByName(name, pageable);
        return ResponseEntity.ok(SuccessResponse.from(data));
    }

    // 상품 반환
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        final var data = productService.getProduct(id);
        return ResponseEntity.ok(SuccessResponse.from(data));
    }

    // 상품 갱신
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDto productRequestDto) {
        final var data = productService.updateProduct(id, productRequestDto);
        return ResponseEntity.ok(SuccessResponse.from(data));
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> productRemove(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.noContent());
    }

    @GetMapping("/category/{categoryCode}")
    public ResponseEntity<?> getProductsByCategoryCode(
            @PathVariable String categoryCode, Pageable pageable) {
        return ResponseEntity.ok(SuccessResponse.from(productService.getProductsByCategory(categoryCode, pageable)));
    }


    @GetMapping("/recommendation")
    public ResponseEntity<?> getRecommendations() {
        return ResponseEntity.ok(SuccessResponse.from(productRecommendService.getRecommendProductsByHeuristic()));
    }

    @GetMapping("/recommendation/member/{memberId}")
    public ResponseEntity<?> getRecommendationsByMemberId(@PathVariable Long memberId) {
        return ResponseEntity.ok(SuccessResponse.from(productRecommendService.getRecommendProductsByCollaborative(memberId)));
    }

    @GetMapping("/recommendation/{productId}")
    public ResponseEntity<?> getRecommendationsByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(SuccessResponse.from(productRecommendService.getRecommendProductsByProductId(productId)));
    }
}
