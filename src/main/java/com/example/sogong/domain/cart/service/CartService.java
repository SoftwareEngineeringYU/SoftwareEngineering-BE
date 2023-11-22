package com.example.sogong.domain.cart.service;


import com.example.sogong.domain.cart.domain.Cart;
import com.example.sogong.domain.cart.dto.request.CartRequestDto;
import com.example.sogong.domain.cart.dto.response.CartItemsRes;
import com.example.sogong.domain.cart.repository.CartRepository;
import com.example.sogong.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService
{
    private final CartRepository cartRepository;

    @Transactional(readOnly = true)
    public CartItemsRes findCartItems(Long userId) {
        List<Cart> carts = cartRepository.findByOwnerId(userId);

        Integer totalPrice = 0;
        List<CartItemsRes.CartItem> items = new ArrayList<>();
        for (Cart cart : carts) {
            CartItemsRes.CartItem item = CartItemsRes.CartItem.fromEntity(cart.getProduct(), cart.getQuantity());
            totalPrice += item.getSubtotalPrice();
            items.add(item);
        }

        return CartItemsRes.of(items, totalPrice);
    }

    @Transactional
    public CartItemsRes updateCart(Long cartId, CartRequestDto cartRequestDto) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException(""));

//        cart.update(cartRequestDto);
//        return new CartViewReq(cart);
        return null;
    }

    @Transactional
    public void deleteCart(Long cartId)
    {
        cartRepository.deleteById(cartId);
    }
}
