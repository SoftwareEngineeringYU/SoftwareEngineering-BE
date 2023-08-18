package com.example.sogong.domain.product.domain;

import com.example.sogong.domain.category.domain.Category;
import com.example.sogong.domain.common.BaseTimeEntity;
import com.example.sogong.domain.image.domain.ImageData;
import com.example.sogong.domain.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@Entity
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Min(value = 0, message = "상품의 가격은 0 이상이어야 합니다")
    private Integer price;

    @NotBlank(message = "상품 설명을 입력해야 합니다")
    @Size(min = 1, max = 200, message = "상품 설명란은 공백이 아니여야 하고 200자 이내여야 합니다")
    private String description;

    @Min(value = 0, message = "상품의 재고량은 0 이상이어야 합니다")
    private Integer stockAmount;

    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @JoinColumn(name = "SELLER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member seller;

    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "product_image",
            joinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    @OrderColumn
    private List<ImageData> images = new ArrayList<>();

    @Builder
    private Product(String name, Integer price, String description, Integer stockAmount, Category category, Member seller) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stockAmount = stockAmount;
        this.category = category;
        this.seller = seller;
    }

}
