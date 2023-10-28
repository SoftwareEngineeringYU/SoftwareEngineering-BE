package com.example.sogong.domain.address.domain;

import com.example.sogong.domain.address.dto.request.AddressRequestDto;
import com.example.sogong.domain.member.domain.Member;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {
    @Size(min = 1, max = 10, message = "주소는 1 ~ 10자 이내여야 합니다")
    private String address;

    @Size(min = 1, max = 10, message = "우편번호는 1 ~ 10자 이내여야 합니다")
    private String zipcode;

    @JoinColumn(name = "ADDRESSEE", nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member addressee;

    @Builder
    public Address(String address, String zipcode, Member addressee) {
        this.address = address;
        this.zipcode = zipcode;
        this.addressee = addressee;
    }

    public void update(AddressRequestDto addressRequestDto) {
        this.address = addressRequestDto.getAddress();
        this.zipcode = addressRequestDto.getZipcode();
        this.addressee = addressRequestDto.getAddressee();
    }

    public Address(AddressRequestDto addressRequestDto) {
        this.address = addressRequestDto.getAddress();
        this.zipcode = addressRequestDto.getZipcode();
        this.addressee = addressRequestDto.getAddressee();
    }
}
