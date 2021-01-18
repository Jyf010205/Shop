package com.shuaibi.shop.common.annotation;

import com.shuaibi.shop.common.config.RedisConfugure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: jianyufeng
 * @description:
 * @date: 2020/10/12 11:17
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RedisConfugure.class)
public @interface EnableRedisHandler {
}
