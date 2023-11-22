package com.example.sogong.domain.cart.service;


import com.example.sogong.domain.cart.domain.Cart;
import com.example.sogong.domain.cart.dto.request.CartRequestDto;
import com.example.sogong.domain.cart.dto.response.CartItemsRes;
import com.example.sogong.domain.cart.repository.CartRepository;
import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.member.repository.MemberRepository;
import com.example.sogong.domain.product.domain.Product;
import com.example.sogong.domain.product.repository.ProductRepository;
import com.example.sogong.global.common.response.code.ErrorCode;
import com.example.sogong.global.common.response.exception.GlobalErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService
{
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
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
    public void addCart(Long userId, Long productId, Integer quantity) {
        if (isExistProductInCart(userId, productId))
            throw new GlobalErrorException(ErrorCode.DUPLICATE_CART_ITEM);

        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new GlobalErrorException(ErrorCode.NOT_FOUND_ERROR));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new GlobalErrorException(ErrorCode.NOT_FOUND_ERROR));

        Cart cart = Cart.of(member, product, quantity);
        cartRepository.save(cart);
    }

    @Transactional
    public void updateCart(Long cartId, String diff) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new GlobalErrorException(ErrorCode.NOT_FOUND_ERROR));

        if (diff.equals("plus")) {
            cart.plusQuantity();
        } else if (diff.equals("minus")) {
            if (cart.getQuantity() <= 0) {
                throw new GlobalErrorException(ErrorCode.BAD_REQUEST_ERROR);
            }
            cart.minusQuantity();
        }
    }

    @Transactional
    public void deleteCart(Long cartId)
    {
        cartRepository.deleteById(cartId);
    }

    private boolean isExistProductInCart(Long userId, Long productId) {
        return cartRepository.existsByOwnerIdAndProductId(userId, productId);
    }
}
