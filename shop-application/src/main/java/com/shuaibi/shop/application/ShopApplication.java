package com.shuaibi.shop.application;

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
 * 文件描述
 *
 * @ProjectName: shuaibishop
 * @Package: com.shuaibi.shop.goods
 * @Description: note
 * @Author: SYQ
 * @CreateDate: 2021/1/14 16:35
 * @UpdateUser: SYQ
 * @UpdateDate: 2021/1/14 16:35
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2021 Hundsun Technologies Inc. All Rights Reserved
 **/
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
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
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
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
