package com.example.sogong.domain.cart.dto.request;

import com.example.sogong.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CartRequestDto {
    private Member owner;
}
