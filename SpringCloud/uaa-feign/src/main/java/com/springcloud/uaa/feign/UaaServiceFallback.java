package com.springcloud.uaa.feign;

import org.springframework.stereotype.Component;

@Component
public class UaaServiceFallback implements UaaServiceFeign{

    @Override
    public String getUsers() {
        return "user service is unavailable.";
    }
}
