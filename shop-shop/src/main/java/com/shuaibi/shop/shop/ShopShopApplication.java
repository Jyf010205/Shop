package com.shuaibi.shop.shop;

import com.shuaibi.shop.common.annotation.EnableRedisHandler;
import com.shuaibi.shop.common.config.WebConfig;
import com.shuaibi.shop.common.utils.JwtTokenUtil;
import com.shuaibi.shop.common.utils.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: jianyufeng
 * @date: 2021/1/20 21:31
 * @description: 禁用SpringSecurity
 */
@EnableAsync
@EnableRedisHandler
@EnableDiscoveryClient
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
})
@EnableTransactionManagement
@MapperScan("com.shuaibi.shop.common.mapper")
public class ShopShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopShopApplication.class, args);
    }

    /**
     * SpringContextHolder依赖注入
     * @return
     */
    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    @Bean
    public WebConfig getWebConfig(){
        return new WebConfig();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil(){
        return new JwtTokenUtil();
    }
}
