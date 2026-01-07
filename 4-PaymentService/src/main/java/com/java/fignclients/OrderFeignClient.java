package com.java.fignclients;

import com.java.dto.OrderResponseDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORDERSERVICE",path = "/orders")
@LoadBalancerClient
public interface OrderFeignClient {

    @GetMapping("/{orderId}")
    OrderResponseDto getOrderById(@PathVariable Long orderId);
}
