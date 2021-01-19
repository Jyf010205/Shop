package com.shuaibi.shop.common.annotation;

import com.shuaibi.shop.common.config.MybatisPageConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * @author: jianyufeng
 * @description: mybatis分页配置 注解注入
 * @date: 2020/7/29 15:52
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MybatisPageConfigure.class)
public @interface EnableMybatisPageHandler {
}
