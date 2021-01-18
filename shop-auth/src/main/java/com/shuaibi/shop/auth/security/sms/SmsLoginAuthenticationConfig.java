package com.shuaibi.shop.auth.security.sms;

import com.shuaibi.shop.auth.security.AuthAuthenticationFailureHandler;
import com.shuaibi.shop.auth.security.AuthAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author: jianyufeng
 * @date: 2021/1/18 15:13
 * @description:
 */
@Component
public class SmsLoginAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsLoginAuthenticationFilter smsLoginAuthenticationFilter = new SmsLoginAuthenticationFilter();
        smsLoginAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsLoginAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        smsLoginAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        SmsLoginAuthenticationProvider smsLoginAuthenticationProvider = new SmsLoginAuthenticationProvider();
        smsLoginAuthenticationProvider.setUserDetailsService(userDetailsService);
        http.authenticationProvider(smsLoginAuthenticationProvider)
                .addFilterAfter(smsLoginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
