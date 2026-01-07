package com.java.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingRequestDto {

    private Long orderId;
    private String shippingMethod; // STANDARD, EXPRESS,NEXT_DAY
    private String carrier; // FedEx, UPS, DHL, BLUE_DART etc.

}
