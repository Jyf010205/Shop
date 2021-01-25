package com.shuaibi.shop.system.security.common;

import com.shuaibi.shop.system.security.AuthAuthenticationFailureHandler;
import com.shuaibi.shop.system.security.AuthAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author: jianyufeng
 * @date: 2021/1/16 14:44
 * @description: 普通登录配置
 */
@Component
public class CommonLoginAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private AuthAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        CommonLoginAuthenticationFilter commonLoginAuthenticationFilter = new CommonLoginAuthenticationFilter();
        commonLoginAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        commonLoginAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        commonLoginAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        CommonLoginAuthenticationProvider commonLoginAuthenticationProvider = new CommonLoginAuthenticationProvider();
        commonLoginAuthenticationProvider.setUserDetailsService(userDetailsService);
        commonLoginAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        http.authenticationProvider(commonLoginAuthenticationProvider)
                .addFilterAfter(commonLoginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
