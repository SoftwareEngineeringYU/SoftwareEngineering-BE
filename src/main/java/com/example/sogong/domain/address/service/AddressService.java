package com.example.sogong.domain.address.service;

import com.example.sogong.domain.address.domain.Address;
import com.example.sogong.domain.address.dto.AddressDto;
import com.example.sogong.domain.address.dto.AddressCreateRequest;
import com.example.sogong.domain.address.repository.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressService
{
    private final AddressRepository addressRepository;

    @Transactional
    public AddressDto getAddress(Long addressId)
    {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException(""));
        return new AddressDto(address);
    }

    @Transactional
    public void createAddress(AddressCreateRequest addressReq)
    {
        addressRepository.save(addressReq.toEntity());
    }

    @Transactional
    public AddressDto updateAddress(Long addressId, AddressDto addressDto)
    {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException(""));

      //  address.update(addressDto);
        return new AddressDto(address);
    }

    @Transactional
    public void deleteAddress(Long addressId)
    {
        /* address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException(""));
        */
        addressRepository.deleteById(addressId);
    }

}
