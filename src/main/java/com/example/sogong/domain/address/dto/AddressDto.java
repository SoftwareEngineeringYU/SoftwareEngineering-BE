package com.example.sogong.domain.address.dto;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
public class AddressDto {
    private String address;

    private String zipcode;

    public AddressDto(String address, String zipcode) {
        this.address = address;
        this.zipcode = zipcode;
    }
}
