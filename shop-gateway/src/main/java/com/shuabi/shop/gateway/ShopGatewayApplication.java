package com.shuabi.shop.gateway;


import com.shuaibi.shop.common.utils.JwtTokenUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

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
@EnableDiscoveryClient
@SpringBootApplication
public class ShopGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopGatewayApplication.class, args);
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil(){
        return new JwtTokenUtil();
    }
}
