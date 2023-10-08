package com.example.sogong.domain.address.controller;


import com.example.sogong.domain.address.dto.AddressCreateRequest;
import com.example.sogong.domain.address.dto.AddressDto;
import com.example.sogong.domain.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController
{
    private final AddressService addressService;


    //조회 - order이랑 member에서 처리??

    //생성
    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody AddressCreateRequest addressReq) {
        addressService.createAddress(addressReq);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(successWithNoContent());
    }


    //주소수정
    @PutMapping
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long addressId, AddressDto addressDto)
    {
        return new ResponseEntity<>(addressService.updateAddress(addressId,addressDto), HttpStatus.OK);
    }

    //등록주소 삭제
    @DeleteMapping
    public void deleteAddress(@PathVariable Long addressId)
    {
        addressService.deleteAddress(addressId);
    }

}
