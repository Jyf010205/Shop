package com.shuaibi.shop.shop.controller;


import com.shuaibi.shop.common.annotation.UserId;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.entity.table.PmsShop;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.shop.entity.request.CreateShopRequest;
import com.shuaibi.shop.shop.entity.request.UpdateShopRequest;
import com.shuaibi.shop.shop.service.IPmsShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 店铺表 前端控制器
 * </p>
 *
 * @author jianyufeng
 * @since 2021-01-29
 */
@RestController
@RequestMapping("/shop")
@Api(tags = "店铺Api")
public class PmsShopController {
    @Autowired
    IPmsShopService pmsShopService;

    @GetMapping
    @ApiOperation("获取店铺列表")
    public CommonResult<List<PmsShop>> getShopList(@ApiIgnore @UserId String userId){
        return CommonResult.success(pmsShopService.pmsShopService(userId),"查询成功");
    }

    @GetMapping("/{id}")
    @ApiOperation("获取店铺信息")
    public CommonResult<PmsShop> getShop(@PathVariable Long id){
        Optional<PmsShop> shop = Optional.ofNullable(pmsShopService.getById(id));
        if (!shop.isPresent()){
            Asserts.fail("店铺不存在");
        }
        return CommonResult.success(shop.get(),"获取店铺信息成功");
    }

    @PostMapping
    @ApiOperation("创建店铺")
    public CommonResult create(@Valid @RequestBody CreateShopRequest request,
                                        @ApiIgnore @UserId String userId){
        Boolean status = pmsShopService.createShop(request, userId);
        if (!status){
            Asserts.fail("创建店铺失败");
        }
        return CommonResult.success(request,"创建店铺成功");
    }

    @PutMapping
    @ApiOperation("修改店铺")
    public CommonResult update(@Valid @RequestBody UpdateShopRequest request,
                                        @ApiIgnore @UserId String userId){
        Boolean status  = pmsShopService.updateShop(request,userId);
        if (!status){
            Asserts.fail("修改店铺失败");
        }
        return CommonResult.success(request,"修改店铺成功");
    }
}
