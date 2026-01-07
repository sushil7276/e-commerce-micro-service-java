package com.java.controller;

import com.java.dto.CartRequestDto;
import com.java.dto.CartResponseDto;
import com.java.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartResponseDto> addItem(@RequestBody CartRequestDto cartRequestDto) {
        CartResponseDto cartResponseDto = cartService.addToCart(cartRequestDto);
        return new ResponseEntity<>(cartResponseDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CartResponseDto> updateQuantity(@RequestBody CartRequestDto cartRequestDto) {
        CartResponseDto cartResponseDto = cartService.updateQuantity(cartRequestDto);
        return ResponseEntity.ok(cartResponseDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartResponseDto>> getCartByUserId(@PathVariable Long userId) {
        List<CartResponseDto> cartByUserId = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartByUserId);
    }

    @DeleteMapping("/remove/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeItem(@RequestParam Long userId, @PathVariable Long productId) {
        cartService.removeItem(userId, productId);
    }

    @DeleteMapping("/clear/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }


}
