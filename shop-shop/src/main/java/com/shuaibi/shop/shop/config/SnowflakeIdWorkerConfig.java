package com.shuaibi.shop.shop.config;

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
    /**
     * 商城ID
     * @return
     */
    @Bean("shopSnowflakeIdWorker")
    public SnowflakeIdWorker shopSnowflakeIdWorker(){
        return new SnowflakeIdWorker(3,1);
    }

    /**
     * 运输模板ID
     * @return
     */
    @Bean("freightSnowflakeIdWorker")
    public SnowflakeIdWorker freightSnowflakeIdWorker(){
        return new SnowflakeIdWorker(3,2);
    }

    /**
     * 商品导入ID
     * @return
     */
    @Bean("productSnowflakeIdWorker")
    public SnowflakeIdWorker productSnowflakeIdWorker(){
        return new SnowflakeIdWorker(3,3);
    }
}
