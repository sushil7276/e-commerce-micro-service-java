package com.java.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRequestDto {

    private Long orderId;
    private Long userId;
    private BigDecimal amount;

}
