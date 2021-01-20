package com.shuaibi.shop.personal.controller;

import com.shuaibi.shop.common.annotation.User;
import com.shuaibi.shop.common.entity.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author: jianyufeng
 * @date: 2021/1/20 21:40
 * @description:
 */
@RestController
@RequestMapping("/personal")
@Api(tags = "个人信息管理")
public class PersonalController {

    @GetMapping
    @ApiOperation("获取个人信息")
    public CommonResult getPersonalInfo(@ApiIgnore @User String userId){
        return CommonResult.success(userId);
    }
}
