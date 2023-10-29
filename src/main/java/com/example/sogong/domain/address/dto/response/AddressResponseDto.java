package com.example.sogong.domain.address.dto.response;

import com.example.sogong.domain.address.domain.Address;
import com.example.sogong.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AddressResponseDto {
    private String address;
    private String zipcode;
    private Member addressee;

    public AddressResponseDto(Address address) {
        this.address = address.getAddress();
        this.zipcode = address.getZipcode();
        this.addressee = address.getAddressee();
    }
}
