package com.example.sogong.domain.address.service;

import com.example.sogong.domain.address.domain.Address;
import com.example.sogong.domain.address.dto.request.AddressRequestDto;
import com.example.sogong.domain.address.dto.response.AddressResponseDto;
import com.example.sogong.domain.address.repository.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Transactional
    public AddressResponseDto createAddress(AddressRequestDto addressRequestDto) {
        return new AddressResponseDto(addressRepository.save(new Address(addressRequestDto)));
    }

    @Transactional
    public AddressResponseDto getAddress(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException(""));
        return new AddressResponseDto(address);
    }

    @Transactional
    public AddressResponseDto updateAddress(Long addressId, AddressRequestDto addressRequestDto) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        address.update(addressRequestDto);
        return new AddressResponseDto(address);
    }

    @Transactional
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }

}
