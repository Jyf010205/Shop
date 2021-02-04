package com.shuaibi.shop.personal;

import com.shuaibi.shop.common.annotation.EnableMybatisPageHandler;
import com.shuaibi.shop.common.annotation.EnableRedisHandler;
import com.shuaibi.shop.common.config.WebConfig;
import com.shuaibi.shop.common.utils.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@EnableMybatisPageHandler
@EnableTransactionManagement
@MapperScan("com.shuaibi.shop.common.mapper")
public class ShopPersonalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopPersonalApplication.class, args);
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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebConfig getWebConfig(){
        return new WebConfig();
    }
}
