package com.java.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class OrderItemResponseDto {
    private  Long productId;
    private  Integer quantity;
    private BigDecimal price;
}
