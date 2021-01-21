package com.shuaibi.shop.personal.controller;

import com.shuaibi.shop.common.annotation.UserId;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.personal.entity.request.ModifyPasswordRequest;
import com.shuaibi.shop.personal.service.IPersonalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author: jianyufeng
 * @date: 2021/1/20 21:40
 * @description:
 */
@RestController
@RequestMapping("/infomation")
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

    @PutMapping
    @ApiOperation("修改个人信息")
    public CommonResult<User> update(@Valid @RequestBody User user,
                                     @ApiIgnore @UserId String userId){
        Integer status = personalService.update(user,userId);
        if (status != 1){
            Asserts.fail("修改失败");
        }
        return CommonResult.success(user,"修改成功");
    }

    @PutMapping("/password")
    @ApiOperation("修改密码")
    public CommonResult modifyPassword(@Valid @RequestBody ModifyPasswordRequest request,
                                       @ApiIgnore @UserId String userId){
        Integer status = personalService.modifyPassword(request,userId);
        if (status != 1){
            Asserts.fail("修改失败");
        }
        return CommonResult.success(null,"修改成功");
    }
}
