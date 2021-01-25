package com.shuaibi.shop.system;

import com.shuaibi.shop.common.annotation.EnableMybatisPageHandler;
import com.shuaibi.shop.common.annotation.EnableRedisHandler;
import com.shuaibi.shop.common.utils.SnowflakeIdWorker;
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
 * @date: 2021/1/8 15:50
 * @description:
 */
@EnableAsync
@EnableRedisHandler
@EnableDiscoveryClient
@SpringBootApplication
@EnableMybatisPageHandler
@EnableTransactionManagement
@MapperScan("com.shuaibi.shop.common.mapper")
public class ShopSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopSystemApplication.class, args);
    }

    /**
     * SpringContextHolder依赖注入
     * @return
     */
    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    /**
     * 分布式雪花算法工具注入
     * @return
     */
    @Bean("UserSnowflakeIdWorker")
    public SnowflakeIdWorker snowflakeIdWorker(){
        return new SnowflakeIdWorker(1,1);
    }
}
