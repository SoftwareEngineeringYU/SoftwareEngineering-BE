package com.example.sogong.domain.address.dto.request;

import com.example.sogong.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AddressRequestDto {
    private String address;
    private String zipcode;
    private Member addressee;
}
