package com.example.sogong.domain.cart.service;


import com.example.sogong.domain.cart.domain.Cart;
import com.example.sogong.domain.cart.dto.CartCreateRequest;
import com.example.sogong.domain.cart.dto.CartDto;
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
    public CartDto getCart(Long cartId)
    {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException(""));
        return new CartDto(cart);
    }

    //생성
    @Transactional
    public void createCart(CartCreateRequest cartReq)
    {
        cartRepository.save(cartReq.toEntity());
    }

    @Transactional
    public CartDto updateCart(Long cartId, CartDto cartDto)
    {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException(""));

       // cart.update(cartDto);
        return new CartDto(cart);
    }

    @Transactional
    public void deleteCart(Long cartId)
    {
        cartRepository.deleteById(cartId);
    }
}
