package com.shuaibi.shop.auth.controller;

import com.shuaibi.shop.auth.entity.request.CommonLoginRequest;
import com.shuaibi.shop.auth.entity.request.SmsLoginRequest;
import com.shuaibi.shop.auth.service.SystemUserService;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.common.utils.Asserts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author: jianyufeng
 * @date: 2021/1/13 16:03
 * @description: 用户管理
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "用户管理")
public class AuthController {
    @Autowired
    private SystemUserService systemUserService;

    /**
     * 专供Swagger生成接口使用
     */
    @ApiOperation(value = "普通登录")
    @PostMapping(value = "/login")
    public void commonLogin(@RequestBody CommonLoginRequest commonLoginRequest){

    }

    /**
     * 专供Swagger生成接口使用
     */
    @ApiOperation(value = "短信验证码登录")
    @PostMapping(value = "/sms/login")
    public void smsLogin(@RequestBody SmsLoginRequest smsLoginRequest){

    }

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public CommonResult<User> register(@Valid @RequestBody User userParam) {
        Optional<User> user = systemUserService.register(userParam);
        if (!user.isPresent()) {
            Asserts.fail("用户注册失败");
        }
        return CommonResult.success(user.get());
    }

    @ApiOperation(value = "发送短信验证码")
    @PostMapping(value = "/sms/code/{mobile}")
    public CommonResult<Long> smsCode(@PathVariable Long mobile){
        return CommonResult.success(systemUserService.smsCode(mobile));
    }

    @ApiOperation(value = "获得当前登陆用户对应的对象")
    @GetMapping(value = "/getAuthentication")
    public CommonResult getAuthentication(){
        return CommonResult.success(SecurityContextHolder.getContext().getAuthentication());
    }
}
