package com.example.sogong.domain.product.service;

import com.example.sogong.domain.product.domain.Product;
import com.example.sogong.domain.product.dto.request.ProductRequestDto;
import com.example.sogong.domain.product.dto.response.ProductResponseDto;
import com.example.sogong.domain.product.repository.ProductRepository;
import com.example.sogong.global.common.response.code.ErrorCode;
import com.example.sogong.global.common.response.exception.GlobalErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        return new ProductResponseDto(productRepository.save(Product.of(productRequestDto)));
    }

    public ProductResponseDto getProduct(Long productId) {
        Product product = findOrThrow(productId);
        return new ProductResponseDto(product);
    }

    public List<ProductResponseDto> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable).getContent()
                .stream()
                .map(ProductResponseDto::new)
                .toList();
    }

    @Transactional
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto productRequestDto) {
        Product product = findOrThrow(productId);
        product.update(productRequestDto);

        return new ProductResponseDto(product);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }


    private Product findOrThrow(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new GlobalErrorException(ErrorCode.NOT_FOUND_ERROR));
    }

}
