package com.example.sogong.domain.address.dto;

import com.example.sogong.domain.address.domain.Address;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AddressDto
{

    @Size(min = 1, max = 10, message = "주소는 1 ~ 10자 이내여야 합니다")
    private String address;

    @Size(min = 1, max = 10, message = "우편번호는 1 ~ 10자 이내여야 합니다")
    private String zipcode;

    public AddressDto(Address address)
    {
        this.address=address.getAddress();
        this.zipcode=address.getZipcode();

    }

}
