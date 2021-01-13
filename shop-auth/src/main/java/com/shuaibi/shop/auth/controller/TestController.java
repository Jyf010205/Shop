package com.shuaibi.shop.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jianyufeng
 * @date: 2021/1/12 17:44
 * @description:
 */
@RestController
public class TestController {
    @GetMapping("hello")
    public String hello() {
        return "hello spring security";
    }
}
