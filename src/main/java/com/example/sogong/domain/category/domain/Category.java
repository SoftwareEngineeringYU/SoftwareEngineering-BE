package com.example.sogong.domain.category.domain;

import com.example.sogong.domain.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@Entity
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 20, message = "카테고리 이름의 길이는 1 ~ 20자 이내여야 합니다")
    private String name;

    @Size(min = 1, max = 16, message = "카테고리 코드의 길이는 1 ~ 16자 이내여야 합니다")
    private String code;

    @Builder
    private Category(String name, String code) {
        this.name = name;
        this.code = code;
    }

}
