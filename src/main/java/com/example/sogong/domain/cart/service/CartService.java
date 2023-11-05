package com.example.sogong.domain.cart.service;


import com.example.sogong.domain.cart.domain.Cart;
import com.example.sogong.domain.cart.dto.request.CartRequestDto;
import com.example.sogong.domain.cart.dto.response.CartResponseDto;
import com.example.sogong.domain.cart.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartService
{
    private final CartRepository cartRepository;

    @Transactional
    public CartResponseDto createCart(CartRequestDto cartRequestDto) {
        return new CartResponseDto(cartRepository.save(new Cart(cartRequestDto)));
    }

    @Transactional
    public CartResponseDto getCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException(""));
        return new CartResponseDto(cart);
    }


    @Transactional
    public CartResponseDto updateCart(Long cartId, CartRequestDto cartRequestDto) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        cart.update(cartRequestDto);
        return new CartResponseDto(cart);
    }

    @Transactional
    public void deleteCart(Long cartId)
    {
        cartRepository.deleteById(cartId);
    }
}
