package com.shuaibi.shop.application.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shuaibi.shop.common.annotation.UserId;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.entity.table.ReceiveAddress;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.application.service.IReceiveAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 收货地址表 前端控制器
 * </p>
 *
 * @author syq
 * @since 2021-01-21
 */
@RestController
@RequestMapping("/address")
@Api(tags = "收货地址管理")
public class ReceiveAddressController {
    @Autowired
    IReceiveAddressService receiveAddressService;

    @GetMapping
    @ApiOperation("获取收货地址列表")
    public CommonResult<List<ReceiveAddress>> getReceiveAddressList(@ApiIgnore @UserId String userId){
        return CommonResult.success(receiveAddressService.list(new LambdaQueryWrapper<ReceiveAddress>().eq(ReceiveAddress::getUserId,userId)));
    }

    @GetMapping("/default")
    @ApiOperation("获取默认收货地址")
    public CommonResult<ReceiveAddress> getDefaultReceiveAddress(@ApiIgnore @UserId String userId){
        return CommonResult.success(receiveAddressService.getOne(new LambdaQueryWrapper<ReceiveAddress>()
                .eq(ReceiveAddress::getUserId,userId)
                .eq(ReceiveAddress::getDefaultStatus,true)));
    }

    @PostMapping
    @ApiOperation("新增收货地址")
    public CommonResult<ReceiveAddress> insert(@Valid @RequestBody ReceiveAddress address, @ApiIgnore @UserId String userId){
        boolean status = receiveAddressService.insert(address,userId);
        if (!status){
            Asserts.fail("新增失败");
        }
        return CommonResult.success(address,"新增成功");
    }

    @PutMapping
    @ApiOperation("修改收货地址")
    public CommonResult<ReceiveAddress> update(@Valid @RequestBody ReceiveAddress address, @ApiIgnore @UserId String userId){
        if (!StrUtil.equals(address.getUserId().toString(),userId)){
            Asserts.fail("只能修改自己的收货地址");
        }
        boolean status = receiveAddressService.updateById(address);
        if (!status){
            Asserts.fail("修改失败");
        }
        return CommonResult.success(address,"修改成功");
    }

    @PutMapping("defaultStatus/{id}")
    @ApiOperation("修改默认地址")
    public CommonResult<Long> updateDefaultStatus(@PathVariable Long id, @ApiIgnore @UserId String userId){
        boolean status = receiveAddressService.updateDefaultStatus(id,userId);
        if (!status){
            Asserts.fail("修改默认地址失败");
        }
        return CommonResult.success(id,"修改默认地址成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除收货地址")
    public CommonResult<Long> delete(@PathVariable Long id, @ApiIgnore @UserId String userId){
        if (!StrUtil.equals(receiveAddressService.getById(id).getUserId().toString(),userId)){
            Asserts.fail("只能删除自己的收货地址");
        }
        boolean status = receiveAddressService.removeById(id);
        if (!status){
            Asserts.fail("删除失败");
        }
        return CommonResult.success(id,"删除成功");
    }
}
