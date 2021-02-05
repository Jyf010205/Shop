package com.shuaibi.shop.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shuaibi.shop.common.entity.BatchRequest;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.system.entity.request.FindUserListRequest;
import com.shuaibi.shop.system.entity.request.InsertUserRequest;
import com.shuaibi.shop.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author: jianyufeng
 * @date: 2021/1/19 14:28
 * @description:
 */
@RestController
@Api(tags = "用户管理")
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping
    @ApiOperation("查询用户列表")
    @PreAuthorize("hasAuthority('user:read')")
    public CommonResult<IPage<User>> findUserList(FindUserListRequest request){
        return CommonResult.success(userService.findUserList(request),"查询成功");
    }

    @GetMapping("/{id}")
    @ApiOperation("查询用户")
    @PreAuthorize("hasAuthority('user:read')")
    public CommonResult<User> find(@PathVariable Long id){
        Optional<User> user = Optional.ofNullable(userService.getById(id));
        if (!user.isPresent()) {
            Asserts.fail("查询失败");
        }
        return CommonResult.success(user.get(),"查询成功");
    }

    @PostMapping
    @ApiOperation("新增用户")
    @PreAuthorize("hasAuthority('user:insert')")
    public CommonResult<User> create(@Valid @RequestBody InsertUserRequest request){
        Optional<User> user = userService.createUser(request);
        if (!user.isPresent()){
            Asserts.fail("创建用户失败");
        }
        return CommonResult.success(user.get(),"创建用户成功");
    }


    @PutMapping()
    @ApiOperation("修改用户")
    @PreAuthorize("hasAuthority('user:update')")
    public CommonResult<User> update(@Valid @RequestBody User user){
        boolean status = userService.updateById(user);
        if (!status){
            Asserts.fail("修改失败");
        }
        return CommonResult.success(user,"修改成功");
    }

    @PutMapping("/able/{id}/{ableStatus}")
    @ApiOperation("修改用户状态")
    @PreAuthorize("hasAuthority('user:update')")
    public CommonResult<Long> ableUser(@PathVariable Long id,@PathVariable Boolean ableStatus){
        boolean status = userService.ableUser(id,ableStatus);
        if (!status){
            Asserts.fail("修改用户状态失败");
        }
        return CommonResult.success(id,"修改用户状态成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    @PreAuthorize("hasAuthority('user:delete')")
    public CommonResult<Long> delete(@PathVariable Long id){
        boolean status = userService.removeById(id);
        if (!status){
            Asserts.fail("删除失败");
        }
        return CommonResult.success(id,"删除成功");
    }

    @PostMapping("/batch")
    @ApiOperation("批量操作")
    @PreAuthorize("hasAuthority('user:read') and hasAuthority('user:update') and hasAuthority('user:delete')")
    public CommonResult batchDelete(@RequestBody BatchRequest request){
        boolean status = userService.batch(request);
        if (!status){
            Asserts.fail("批量操作失败");
        }
        return CommonResult.success(null,"批量操作成功");
    }
}
