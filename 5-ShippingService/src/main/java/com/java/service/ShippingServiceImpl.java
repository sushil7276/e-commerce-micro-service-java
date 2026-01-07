package com.java.service;

import com.java.dto.OrderResponseDto;
import com.java.dto.ShippingRequestDto;
import com.java.dto.ShippingResponseDto;
import com.java.exception.ResourceNotFoundException;
import com.java.feignclients.OrderFeignClient;
import com.java.models.ShippingInfo;
import com.java.repository.ShippingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Override
    public ShippingResponseDto shipOrder(ShippingRequestDto shippingRequestDto) {

        // Fetch order details via Feign client
        OrderResponseDto orderById = orderFeignClient.getOrderById(shippingRequestDto.getOrderId());
        if (orderById == null) {
            throw new ResourceNotFoundException("Order not found with id: " + shippingRequestDto.getOrderId());
        }

        ShippingInfo shippingInfo = new ShippingInfo();
        BeanUtils.copyProperties(shippingRequestDto, shippingInfo);
        shippingInfo.setStatus("SHIPPED");
        shippingInfo.setShippedAt(LocalDateTime.now());
        ShippingInfo dbShippingInfo = shippingRepository.save(shippingInfo);

        // Update order status via Feign client
        orderFeignClient.updateOrderStatus(shippingInfo.getOrderId(), "SHIPPED");


        return mapToDto(dbShippingInfo);
    }

    private ShippingResponseDto mapToDto(ShippingInfo shippingInfo) {
        ShippingResponseDto dto = new ShippingResponseDto();
        BeanUtils.copyProperties(shippingInfo, dto);
        dto.setShippingId(shippingInfo.getId());
        return dto;
    }

    @Override
    public ShippingResponseDto updateShippingStatus(Long orderId, String status) {

        ShippingInfo shippingInfo = shippingRepository.findByOrderId(orderId);

        if (shippingInfo == null) {
            throw new ResourceNotFoundException("Shipping info not found for order id: " + orderId);
        }

        shippingInfo.setStatus(status);
        shippingInfo.setDeliveryDate("DELIVERED".equals(status)
                ? LocalDateTime.now() : null);
        ShippingInfo dbShippingInfo = shippingRepository.save(shippingInfo);

        if ("DELIVERED".equals(status)) {
            // Update order status via Feign client
            orderFeignClient.updateOrderStatus(orderId, "DELIVERED");
        }
        return mapToDto(dbShippingInfo);
    }
}
