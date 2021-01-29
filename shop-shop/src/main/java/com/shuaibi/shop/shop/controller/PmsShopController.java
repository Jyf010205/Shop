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

    @PostMapping
    @ApiOperation("创建店铺")
    public CommonResult<PmsShop> create(@RequestBody CreateShopRequest request,
                               @ApiIgnore @UserId String userId){
        Optional<PmsShop> shop = pmsShopService.createShop(request, userId);
        if (!shop.isPresent()){
            Asserts.fail("创建店铺失败");
        }
        return CommonResult.success(shop.get(),"创建店铺成功");
    }

    @PutMapping
    @ApiOperation("修改店铺")
    public CommonResult update(@RequestBody UpdateShopRequest request,
                               @ApiIgnore @UserId String userId){
        Optional<PmsShop> shop = pmsShopService.updateShop(request,userId);
        if (!shop.isPresent()){
            Asserts.fail("修改店铺失败");
        }
        return CommonResult.success(shop.get(),"修改店铺成功");
    }
}
