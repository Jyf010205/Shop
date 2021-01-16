package com.shuaibi.shop.auth.config;

import com.shuaibi.shop.auth.entity.SystemUserDetails;
import com.shuaibi.shop.auth.security.JwtAuthenticationTokenFilter;
import com.shuaibi.shop.auth.security.RestAuthenticationEntryPoint;
import com.shuaibi.shop.auth.security.RestfulAccessDeniedHandler;
import com.shuaibi.shop.auth.security.common.JwtLoginAuthenticationConfig;
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
 * @description: SpringSecurity的配置 原理分析https://wangsong.blog.csdn.net/article/details/89629415
 *
 * 对于一次请求:
 * 这些URL无关过滤器，过滤器每个都会执行，先执行完所有过滤器再执行请求
 * AbstractAuthenticationProcessingFilter过滤器有
 *      successfulAuthentication() AuthenticationSuccessHandler登录成功
 *      unsuccessfulAuthentication() AuthenticationFailureHandler登录失败
 *      两种处理器中断过滤链，直接返回结果
 * 授权访问：在一个filter中，会从认证上下文中获取authentication确保认证通过
 * 无授权访问：则反之，即不会从上下文获取authentication
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    JwtLoginAuthenticationConfig jwtLoginAuthenticationConfig;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            //添加Jwt登录configurer
            http.apply(jwtLoginAuthenticationConfig)
                //由于使用的是JWT，不需要csrf
                .and().csrf().disable()
                //基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //禁用缓存
                .and().headers().cacheControl().disable()
                .and().authorizeRequests()
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
                //对登录注册要允许匿名访问
                .antMatchers("/admin/login", "/admin/register").permitAll()
                //跨域请求会先进行一次options请求
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                //除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                //添加自定义未授权和未登录结果返回
                .and().exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                //添加请求头校验token的过滤器
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
