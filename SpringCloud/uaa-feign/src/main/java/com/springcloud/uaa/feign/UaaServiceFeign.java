package com.springcloud.uaa.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "uaa-service")
public interface UaaServiceFeign {

    @GetMapping("/user/list")
    String getUsers();
}
