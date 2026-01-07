package com.java.service;

import com.java.dto.CartRequestDto;
import com.java.dto.CartResponseDto;

import java.util.List;

public interface CartService {

    CartResponseDto addToCart(CartRequestDto cartRequestDto);

    List<CartResponseDto> getCartByUserId(Long userId);

    void removeItem(Long userId, Long productId);

    CartResponseDto updateQuantity(CartRequestDto cartRequestDto);

    void clearCart(Long userId);

}
