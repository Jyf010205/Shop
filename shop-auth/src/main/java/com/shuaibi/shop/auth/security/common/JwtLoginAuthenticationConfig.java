package com.shuaibi.shop.auth.security.common;

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
 * @description:
 */
@Component
public class JwtLoginAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private JwtLoginAuthenticationSuccessHandler jwtLoginAuthenticationSuccessHandler;
    @Autowired
    private JwtLoginAuthenticationFailureHandler jwtLoginAuthenticationFailureHandler;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtLoginAuthenticationFilter jwtLoginAuthenticationFilter = new JwtLoginAuthenticationFilter();
        jwtLoginAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        jwtLoginAuthenticationFilter.setAuthenticationSuccessHandler(jwtLoginAuthenticationSuccessHandler);
        jwtLoginAuthenticationFilter.setAuthenticationFailureHandler(jwtLoginAuthenticationFailureHandler);

        JwtLoginAuthenticationProvider jwtLoginAuthenticationProvider = new JwtLoginAuthenticationProvider();
        jwtLoginAuthenticationProvider.setUserDetailsService(userDetailsService);
        jwtLoginAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        http.authenticationProvider(jwtLoginAuthenticationProvider)
                .addFilterAfter(jwtLoginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
