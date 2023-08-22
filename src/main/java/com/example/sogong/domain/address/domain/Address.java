package com.example.sogong.domain.address.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Address {
    @Size(min = 1, max = 10, message = "주소는 1 ~ 10자 이내여야 합니다")
    private String address;

    @Size(min = 1, max = 10, message = "우편번호는 1 ~ 10자 이내여야 합니다")
    private String zipcode;

    public Address(String address, String zipcode) {
        this.address = address;
        this.zipcode = zipcode;
    }

}
