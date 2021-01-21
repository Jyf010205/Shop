package com.shuabi.shop.geteway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: shuaibishop
 * @Package: com.shuabi.shop.geteway
 * @Description: note
 * @Author: SYQ
 * @CreateDate: 2021/1/21 15:23
 * @UpdateUser: SYQ
 * @UpdateDate: 2021/1/21 15:23
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 *
 **/

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayNacosConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayNacosConfigApplication.class, args);
    }
}
