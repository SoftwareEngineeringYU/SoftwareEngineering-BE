package com.example.sogong.domain.address.api;


import com.example.sogong.domain.address.application.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
    private final AddressService addressService;

}
