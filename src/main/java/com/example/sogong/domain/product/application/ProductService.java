package com.example.sogong.domain.product.application;

import com.example.sogong.domain.product.dao.ProductRepository;
import com.example.sogong.domain.product.domain.Product;
import com.example.sogong.domain.product.dto.request.ProductRequestDto;
import com.example.sogong.domain.product.dto.response.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(productRequestDto);

        return new ProductResponseDto(productRepository.save(product));
    }

    @Transactional
    public ProductResponseDto getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        return new ProductResponseDto(product);
    }

    @Transactional
    public Page<ProductResponseDto> getAllProduct(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(getSortDirection(sort), "createdAt"));

        return productRepository.findAll(pageable).map(ProductResponseDto::new);
    }

    @Transactional
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        product.update(productRequestDto);

        return new ProductResponseDto(product);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }



    // 오름차순, 내림차순 설정
    private Sort.Direction getSortDirection(String sort) {
        String[] parts = sort.split(",");
        if (parts.length > 1) {
            if ("desc".equalsIgnoreCase(parts[1])) {
                return Sort.Direction.DESC;
            }
        }
        return Sort.Direction.ASC;
    }
}
