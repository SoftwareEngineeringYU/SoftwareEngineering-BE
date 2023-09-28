package com.example.sogong.domain.product.service;

import com.example.sogong.domain.product.repository.ProductRepository;
import com.example.sogong.domain.product.domain.Product;
import com.example.sogong.domain.product.dto.CreationProductReq;
import com.example.sogong.domain.product.dto.GetProductRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void saveProduct(CreationProductReq request) {
        productRepository.save(request.toEntity());
    }

    public List<GetProductRes> findAll() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(GetProductRes::of)
                .toList();
    }

    public Optional<GetProductRes> findProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(GetProductRes::of);
    }

    public void modifyProduct(Long id, CreationProductReq request) {
        if (!productRepository.existsById(id))
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
    }

    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }
}
