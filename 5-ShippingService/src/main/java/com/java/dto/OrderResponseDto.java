package com.java.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderResponseDto {
    private Long orderId;
    private Long userId;
    private String status;
    private BigDecimal totalPrice;
}
