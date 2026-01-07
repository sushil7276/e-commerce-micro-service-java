package com.java.service;

import com.java.dto.OrderResponseDto;
import com.java.dto.OrderStatusResponseDto;
import com.java.dto.PlaceOrderDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto placeOrder(PlaceOrderDto placeOrderDto);

    OrderStatusResponseDto updateOrderStatus(Long orderId, String status);

    List<OrderResponseDto> getOrdersByUserId(Long userId);

    OrderResponseDto getOrderById(Long orderId);
}
