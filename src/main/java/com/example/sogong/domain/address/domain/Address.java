package com.example.sogong.domain.address.domain;

import com.example.sogong.domain.address.dto.request.AddressRequestDto;
import com.example.sogong.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

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
