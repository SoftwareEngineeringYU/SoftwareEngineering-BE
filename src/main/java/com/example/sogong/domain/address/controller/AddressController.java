package com.example.sogong.domain.address.controller;


import com.example.sogong.domain.address.dto.request.AddressRequestDto;
import com.example.sogong.domain.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
    private final AddressService addressService;

    // 주소 생성
    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody AddressRequestDto addressRequestDto) {
        return new ResponseEntity<>(addressService.createAddress(addressRequestDto), HttpStatus.OK);
    }

    // 주소 반환
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(addressService.getAddress(id), HttpStatus.OK);
    }

    // 주소 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(
            @PathVariable Long id,
            @RequestBody AddressRequestDto addressRequestDto) {
        return new ResponseEntity<>(addressService.updateAddress(id, addressRequestDto), HttpStatus.OK);
    }


    // 주소 삭제
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }

}
