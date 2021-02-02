package com.shuabi.shop.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author: jianyufeng
 * @date: 2021/2/2 12:53
 * @description: 关闭springSecurity过滤链
 */

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {

        return http.authorizeExchange()
                .anyExchange()
                .permitAll()
        .and().csrf().disable()
                .httpBasic().disable()
                .logout().disable()
                .formLogin().disable().build();
    }

}
