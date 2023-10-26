package com.example.sogong.domain.product.api;


import com.example.sogong.domain.product.application.ProductService;
import com.example.sogong.domain.product.dto.request.ProductRequestDto;
import com.example.sogong.domain.product.dto.response.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    // 상품 생성
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto createdProduct = productService.createProduct(productRequestDto);

        return new ResponseEntity<>(createdProduct, HttpStatus.OK);
    }

    // 상품 전체 반환
    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,asc") String sort
    ) {
        return new ResponseEntity<>(productService.getAllProduct(page, size, sort), HttpStatus.OK);
    }

    // 상품 반환
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

    // 상품 갱신
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(productService.updateProduct(productId, productRequestDto), HttpStatus.OK);
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);


    }
}
