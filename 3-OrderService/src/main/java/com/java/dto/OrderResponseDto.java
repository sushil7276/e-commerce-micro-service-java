package com.java.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {

    private Long orderId;
    private Long userId;
    private UserDto userDto;
    private String status;
    private BigDecimal totalPrice;
    private List<OrderItemResponseDto> items;
}
