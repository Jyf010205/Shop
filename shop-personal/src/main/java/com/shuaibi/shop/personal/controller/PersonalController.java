package com.shuaibi.shop.personal.controller;

import com.shuaibi.shop.common.annotation.UserId;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.personal.servie.IPersonalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

/**
 * @author: jianyufeng
 * @date: 2021/1/20 21:40
 * @description:
 */
@RestController
@RequestMapping("/personal")
@Api(tags = "个人信息管理")
public class PersonalController {
    @Autowired
    IPersonalService personalService;

    @GetMapping
    @ApiOperation("获取个人信息")
    public CommonResult<User> getPersonalInfo(@ApiIgnore @UserId String userId){
        Optional<User> user = Optional.ofNullable(personalService.getPersonalInfo(userId));
        if (!user.isPresent()) {
            Asserts.fail("获取失败");
        }
        return CommonResult.success(user.get(),"获取成功");
    }
}
