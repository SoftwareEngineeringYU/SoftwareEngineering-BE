package com.example.sogong.domain.address.dto;

import com.example.sogong.domain.address.domain.Address;
import com.example.sogong.domain.member.domain.Member;

public record AddressCreateRequest(String address, String zipcode, Member addressee)
{
    public Address toEntity()
    {
        return Address.builder()
                .address(address)
                .zipcode(zipcode)
                .addressee(addressee)
                .build();
    }
}
