package com.java.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderStatusResponseDto {

    private Long orderId;
    private Long userId;
    private String status;
    private BigDecimal totalPrice;
}
