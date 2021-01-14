package com.shuaibi.shop.auth.config;

import com.shuaibi.shop.auth.entity.SystemUserDetails;
import com.shuaibi.shop.auth.security.JwtAuthenticationTokenFilter;
import com.shuaibi.shop.auth.security.RestAuthenticationEntryPoint;
import com.shuaibi.shop.auth.security.RestfulAccessDeniedHandler;
import com.shuaibi.shop.auth.service.SystemUserService;
import com.shuaibi.shop.common.entity.table.Permission;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.common.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;


/**
 * @author: jianyufeng
 * @date: 2021/1/13 16:00
 * @description: SpringSecurity的配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()// 由于使用的是JWT，不需要csrf
                .disable()
                .sessionManagement()// 基于token，所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers()
                .cacheControl().disable()// 禁用缓存
                .and()
                .authorizeRequests()
                /**
                 * 对于一次请求:
                 * 这些URL无关过滤器，过滤器每个都会执行
                 * 授权访问：在一个filter中，会从认证上下文中获取authentication确保认证通过
                 * 无授权访问：则反之，即不会从上下文获取authentication
                 */
                .antMatchers( // 允许对于网站静态资源的无授权访问
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                ).permitAll()
                .antMatchers("/admin/login", "/admin/register").permitAll()// 对登录注册要允许匿名访问
                .antMatchers(HttpMethod.OPTIONS).permitAll()//跨域请求会先进行一次options请求
                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
                .authenticated()
                //添加自定义未授权和未登录结果返回
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                // 添加JWT filter
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            User user = systemUserService.getAdminByUsername(username);
            if (user != null) {
                if (user.getStatus().equals(User.STATUS_LOCK)){
                    Asserts.fail("用户已被锁定，请联系管理员");
                }
                List<Permission> permissionList = systemUserService.getPermissionList(user.getUserId());
                return new SystemUserDetails(user,permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil(){
        return new JwtTokenUtil();
    }

}
