package com.java.fignclients;

import com.java.dto.UserDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USERSERVICE", path = "/api/users")
@LoadBalancerClient
public interface UserFeignClient {

    @GetMapping("/{userId}")
    UserDto findById(@PathVariable Integer userId);
}
