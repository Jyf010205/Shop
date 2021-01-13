package com.shuaibi.shop.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: jianyufeng
 * @date: 2021/1/8 15:50
 * @description:
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.shuaibi.shop.auth.mapper")
public class ShopAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopAuthApplication.class, args);
    }
}
