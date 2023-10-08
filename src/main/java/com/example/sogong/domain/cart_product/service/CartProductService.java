package com.example.sogong.domain.cart_product.service;


import com.example.sogong.domain.cart_product.domain.CartProduct;
import com.example.sogong.domain.cart_product.dto.CartProductDto;
import com.example.sogong.domain.cart_product.dto.CartProductCreateRequest;
import com.example.sogong.domain.cart_product.repository.CartProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartProductService
{
    private final CartProductRepository cartProductRepository;

    //생성
    @Transactional
    public void createCartProduct(CartProductCreateRequest cartProductReq)
    {
        cartProductRepository.save(cartProductReq.toEntity());
    }
    //조회
    @Transactional
    public CartProductDto getCartProduct(Long cartProductId)
    {
        CartProduct cartProduct = cartProductRepository.findById(cartProductId)
                .orElseThrow(() -> new IllegalArgumentException(""));
        return new CartProductDto(cartProduct);
    }

    //수정
    @Transactional
    public CartProductDto updateCartProduct(Long cartProductId, CartProductDto cartProductDto)
    {
        CartProduct cartProduct = cartProductRepository.findById(cartProductId)
                .orElseThrow(() -> new IllegalArgumentException(""));

     //   cartProduct.update(cartProductDto);
        return new CartProductDto(cartProduct);
    }

    // 테이블에서 지워지므로 카트에서 삭제..?
    @Transactional
    public void deleteCartProduct(Long cartProductId)
    {
       // CartProduct cartProduct= cartProductRepository.findById(cartProductId)
       //         .orElseThrow(()->new IllegalArgumentException(""));

        cartProductRepository.deleteById(cartProductId);
    }

}
