package com.shuaibi.shop.auth.controller;

import cn.hutool.json.JSONObject;
import com.shuaibi.shop.auth.entity.request.UserLoginParam;
import com.shuaibi.shop.auth.service.SystemUserService;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.common.utils.Asserts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    public CommonResult<User> register(@Valid @RequestBody User userParam) {
        Optional<User> user = systemUserService.register(userParam);
        if (!user.isPresent()) {
            Asserts.fail("用户注册失败");
        }
        return CommonResult.success(user.get());
    }

    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "/login")
    public CommonResult login(@Valid @RequestBody UserLoginParam userLoginParam) {
        Optional<String> token = systemUserService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        if (!token.isPresent()) {
            return CommonResult.failed("用户名或密码错误");
        }
        systemUserService.updateLoginTime(userLoginParam.getUsername());
        JSONObject tokenJson = new JSONObject().set("token", token.get()).set("tokenHead", tokenHead);
        return CommonResult.success(tokenJson);
    }

    @ApiOperation(value = "获得当前登陆用户对应的对象")
    @GetMapping(value = "/getAuthentication")
    public CommonResult getAuthentication(){
        return CommonResult.success(SecurityContextHolder.getContext().getAuthentication());
    }
}
