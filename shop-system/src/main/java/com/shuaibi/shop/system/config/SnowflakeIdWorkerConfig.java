package com.shuaibi.shop.system.config;

import com.shuaibi.shop.common.utils.SnowflakeIdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: jianyufeng
 * @date: 2021/1/29 15:28
 * @description: 分布式雪花算法工具注入
 */
@Configuration
public class SnowflakeIdWorkerConfig {
    @Bean("UserSnowflakeIdWorker")
    public SnowflakeIdWorker snowflakeIdWorker(){
        return new SnowflakeIdWorker(0,1);
    }
}
