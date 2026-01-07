package com.java.fignclients;

import com.java.dto.CartResponseDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "2-CARTSERVICE", path = "/api/cart")
@LoadBalancerClient
public interface CartFeignClient {

    @GetMapping("/{userId}")
    List<CartResponseDto> getCartByUserId(@PathVariable Long userId);

    @DeleteMapping("/clear/{userId}")
    void clearCart(@PathVariable Long userId);
}
