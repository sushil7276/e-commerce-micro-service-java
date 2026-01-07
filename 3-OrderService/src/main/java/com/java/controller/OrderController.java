package com.java.controller;

import com.java.dto.OrderResponseDto;
import com.java.dto.OrderStatusResponseDto;
import com.java.dto.PlaceOrderDto;
import com.java.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderResponseDto placeOrder(@RequestBody PlaceOrderDto placeOrderDto) {
        log.info("Placing order for userId: {}", placeOrderDto.getUserId());
        return orderService.placeOrder(placeOrderDto);
    }

    @PutMapping("/{orderId}/status/{status}")
    public ResponseEntity<OrderStatusResponseDto> updateOrderStatus(@PathVariable Long orderId,
                                                                    @PathVariable String status) {
        log.info("Updating order status for orderId: {} to status: {}", orderId, status);
        OrderStatusResponseDto responseDto = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByUserId(@PathVariable Long userId) {
        log.info("Fetching orders for userId: {}", userId);
        List<OrderResponseDto> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long orderId) {
        log.info("Fetching order details for orderId: {}", orderId);
        OrderResponseDto orderResponseDto = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderResponseDto);
    }
}
