package com.java.service;

import com.java.dto.ShippingRequestDto;
import com.java.dto.ShippingResponseDto;

public interface ShippingService {
    ShippingResponseDto shipOrder(ShippingRequestDto shippingRequestDto);
    ShippingResponseDto updateShippingStatus(Long orderId, String status);
}
