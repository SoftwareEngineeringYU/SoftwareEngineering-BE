package com.example.sogong.domain.address.application;

import com.example.sogong.domain.address.dao.AddressRepository;
import com.example.sogong.domain.address.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;


}
