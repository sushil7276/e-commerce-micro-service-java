package com.java.feignclients;

import com.java.dto.OrderResponseDto;
import com.java.dto.OrderStatusResponseDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "ORDERSERVICE", path = "/orders")
@LoadBalancerClient
public interface OrderFeignClient {

    @GetMapping("/{orderId}")
    OrderResponseDto getOrderById(@PathVariable Long orderId);

    @PutMapping("/{orderId}/status/{status}")
    OrderStatusResponseDto updateOrderStatus(@PathVariable Long orderId,
                                             @PathVariable String status);


}
