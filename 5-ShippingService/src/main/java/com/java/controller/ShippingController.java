package com.java.controller;

import com.java.dto.ShippingRequestDto;
import com.java.dto.ShippingResponseDto;
import com.java.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shipping")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @PostMapping()
    public ResponseEntity<ShippingResponseDto> shipOrder(@RequestBody ShippingRequestDto shippingRequestDto) {

        ShippingResponseDto shippingResponseDto = shippingService.shipOrder(shippingRequestDto);
        return ResponseEntity.ok(shippingResponseDto);
    }
}
