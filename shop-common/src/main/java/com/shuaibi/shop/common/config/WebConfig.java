package com.shuaibi.shop.common.config;

import com.shuaibi.shop.common.interceptor.UserInfoResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author: jianyufeng
 * @date: 2021/1/20 21:46
 * @description: 拦截器注入UserId
 */
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserInfoResolver());
    }
}
