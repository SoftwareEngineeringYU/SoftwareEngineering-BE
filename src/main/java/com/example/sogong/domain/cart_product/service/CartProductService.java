package com.example.sogong.domain.cart_product.service;


import com.example.sogong.domain.cart_product.domain.CartProduct;
import com.example.sogong.domain.cart_product.dto.request.CartProductRequestDto;
import com.example.sogong.domain.cart_product.dto.response.CartProductResponseDto;
import com.example.sogong.domain.cart_product.repository.CartProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartProductService
{
    private final CartProductRepository cartProductRepository;

    @Transactional
    public CartProductResponseDto createCartProduct(CartProductRequestDto cartProductRequestDto)
    {
        return new CartProductResponseDto(cartProductRepository.save(new CartProduct(cartProductRequestDto)));
    }
    @Transactional
    public CartProductResponseDto getCartProduct(Long cartProductId)
    {
        CartProduct cartProduct = cartProductRepository.findById(cartProductId)
                .orElseThrow(() -> new IllegalArgumentException(""));
        return new CartProductResponseDto(cartProduct);
    }

    @Transactional
    public CartProductResponseDto updateCartProduct(Long cartProductId, CartProductRequestDto cartProductRequestDto)
    {
        CartProduct cartProduct = cartProductRepository.findById(cartProductId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        cartProduct.update(cartProductRequestDto);
        return new CartProductResponseDto(cartProduct);
    }

    @Transactional
    public void deleteCartProduct(Long cartProductId)
    {
        cartProductRepository.deleteById(cartProductId);
    }

}
