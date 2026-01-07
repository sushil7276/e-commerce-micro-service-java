package com.java.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ShippingResponseDto {

    private Long shippingId;
    private Long orderId;
    private String shippingMethod; // STANDARD, EXPRESS,NEXT_DAY
    private String status; // SHIPPED, IN_TRANSIT ,DELIVERED
    private String carrier; // FedEx, UPS, DHL, BLUE_DART etc.
    private LocalDateTime shippedAt;
    private LocalDateTime deliveryDate;


}
