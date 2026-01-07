package com.java.service;

import com.java.dto.CartRequestDto;
import com.java.dto.CartResponseDto;
import com.java.exception.ProductNotFoundException;
import com.java.exception.UserNotFoundException;
import com.java.feignclient.ProductFeignClient;
import com.java.feignclient.UserFeignClient;
import com.java.model.CartItem;
import com.java.repository.CartRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public CartResponseDto addToCart(CartRequestDto cartRequestDto) {

        // TODO : Further implement user and product valid or not
        boolean productExits = productFeignClient.isProductExits(cartRequestDto.getProductId());
        if (!productExits) {
            throw new ProductNotFoundException("Product does not exits in Db.");
        }

        boolean userExits = userFeignClient.isUserExits(cartRequestDto.getUserId().intValue());
        if (!userExits) {
            throw new UserNotFoundException("User does not exits in Db.");
        }

        CartItem cartItem = new CartItem();
        BeanUtils.copyProperties(cartRequestDto, cartItem);
        CartItem dbCart = cartRepository.save(cartItem);

        return mapToCartResponseDto(dbCart);
    }

    private CartResponseDto mapToCartResponseDto(CartItem cartItem) {
        CartResponseDto cartResponseDto = new CartResponseDto();
        BeanUtils.copyProperties(cartItem, cartResponseDto);
        return cartResponseDto;
    }

    @Override
    public List<CartResponseDto> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).stream()
                .map(this::mapToCartResponseDto)
                .toList();

    }

    @Override
    @Transactional
    public void removeItem(Long userId, Long productId) {

        cartRepository.deleteAllByUserIdAndProductId(userId, productId);
    }

    @Override
    public CartResponseDto updateQuantity(CartRequestDto request) {

        CartItem cartItem = cartRepository
                .findByUserIdAndProductId(request.getUserId(), request.getProductId())
                .orElseThrow(() -> new RuntimeException("Item not in the cart"));
        cartItem.setQuantity(request.getQuantity());
        cartRepository.save(cartItem);
        return mapToCartResponseDto(cartItem);
    }

    @Override
    @Transactional
    public void clearCart(Long userId) {

        cartRepository.deleteAllByUserId(userId);
    }
}
