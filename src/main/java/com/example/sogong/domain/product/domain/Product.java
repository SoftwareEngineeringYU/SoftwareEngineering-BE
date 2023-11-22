package com.example.sogong.domain.product.domain;

import com.example.sogong.domain.cart.domain.Cart;
import com.example.sogong.domain.category.domain.Category;
import com.example.sogong.domain.common.BaseTimeEntity;
import com.example.sogong.domain.image.domain.ImageData;
import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.product.dto.request.ProductRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Min(value = 0, message = "상품의 가격은 0 이상이어야 합니다")
    private Integer price;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    @Min(value = 0, message = "상품의 재고량은 0 이상이어야 합니다")
    private Integer stockAmount;

    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @JoinColumn(name = "SELLER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member seller;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "product_image",
            joinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    @OrderColumn
    private List<ImageData> images;

    @Builder
    protected Product(String name, Integer price, String description, Integer stockAmount, Category category, Member seller, List<ImageData> images) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stockAmount = stockAmount;
        this.category = category;
        this.seller = seller;
        this.images = images;
    }

    public static Product of(ProductRequestDto productRequestDto) {
        return Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .description(productRequestDto.getDescription())
                .stockAmount(productRequestDto.getStockAmount())
                .category(productRequestDto.getCategory())
                .seller(productRequestDto.getSeller())
                .images(productRequestDto.getImages())
                .build();
    }

    public void update(ProductRequestDto productRequestDto) {
        this.name = productRequestDto.getName();
        this.price = productRequestDto.getPrice();
        this.description = productRequestDto.getDescription();
        this.stockAmount = productRequestDto.getStockAmount();
        this.category = productRequestDto.getCategory();
        this.seller = productRequestDto.getSeller();
        this.images = productRequestDto.getImages();
    }


}
