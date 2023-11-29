package com.example.sogong.domain.product.service;

import com.example.sogong.domain.product.dto.response.ProductResponseDto;
import com.example.sogong.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductRecommendService {

    @Value("${sogong.recommend-api}")
    private String recommendApi;

    private final RestTemplate restTemplate;
    private final ProductRepository productRepository;

    public List<ProductResponseDto> getRecommendProductsByHeuristic() {
        List<Long> productIds = getIds(recommendApi + "/heuristic");
        if (productIds == null || productIds.isEmpty()) {
            return List.of();
        }
        return toDtos(productIds);
    }

    public List<ProductResponseDto> getRecommendProductsByProductId(long productId) {
        List<Long> productIds = getIds(recommendApi + "/contents?product_id" + productId);
        return toDtos(productIds);
    }

    public List<ProductResponseDto> getRecommendProductsByCollaborative(Long memberId) {
        List<Long> productIds = getIds(recommendApi + "/collaborative?user_id=" + memberId);
        return toDtos(productIds);
    }


    private List<Long> getIds(String url) {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Long>>() {
                }
        ).getBody();
    }

    private List<ProductResponseDto> toDtos(List<Long> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return List.of();
        }
        return productIds.stream()
                .map(productRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ProductResponseDto::new)
                .toList();
    }

}
