package com.java.feignclient;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCTSERVICE", path = "/api/products")
@LoadBalancerClient
public interface ProductFeignClient {

    @GetMapping("/exits/{productId}")
    boolean isProductExits(@PathVariable Long productId);
}
