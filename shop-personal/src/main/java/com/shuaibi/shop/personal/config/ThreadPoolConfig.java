package com.shuaibi.shop.personal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: jianyufeng
 * @date: 2020/9/3 18:02
 * @description: 线程池配置
 */
@Component
public class ThreadPoolConfig {

    @Bean
    public TaskExecutor getExpiredFreePlayerHandlerThreadPool(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        //cpu核数 * 2 + 1
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(128);
        executor.setKeepAliveSeconds(60);
        executor.setQueueCapacity(256);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
