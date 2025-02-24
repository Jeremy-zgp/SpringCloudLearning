package com.springcloud.product.controller;

import com.springcloud.uaa.feign.UaaServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private UaaServiceFeign uaaServiceFeign;

    @GetMapping("/list")
    public String list(){
        uaaServiceFeign.getUsers();
        return "/product/list";
    }
}
