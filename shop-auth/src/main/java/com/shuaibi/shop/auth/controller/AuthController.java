package com.shuaibi.shop.auth.controller;

import com.shuaibi.shop.auth.entity.UserLoginParam;
import com.shuaibi.shop.auth.service.SystemUserService;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.common.entity.utils.SpringContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jianyufeng
 * @date: 2021/1/13 16:03
 * @description: 用户管理
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "AuthController", description = "用户管理")
public class AuthController {
    @Autowired
    private SystemUserService systemUserService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public CommonResult<User> register(@RequestBody User userParam, BindingResult result) {
        User user = systemUserService.register(userParam);
        if (user == null) {
            CommonResult.failed();
        }
        return CommonResult.success(user);
    }

    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "/login")
    public CommonResult login(@RequestBody UserLoginParam userLoginParam, BindingResult result) {
        String token = systemUserService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        if (token == null) {
            return CommonResult.failed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获得当前登陆用户对应的对象")
    @GetMapping(value = "/getAuthentication")
    public CommonResult getAuthentication(){
        return CommonResult.success(SecurityContextHolder.getContext().getAuthentication());
    }
}
