package com.shuabi.shop.gateway.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: jianyufeng
 * @description: 白名单配置类
 * @date: 2020/7/28 11:18
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:shop-gateway.properties"})
@ConfigurationProperties(prefix = "shop.gateway")
public class ShopGatewayProperties {

    //免认证路径
    private String anonUrl;
}
