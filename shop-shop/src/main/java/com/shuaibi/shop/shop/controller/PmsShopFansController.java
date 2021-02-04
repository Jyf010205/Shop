package com.shuaibi.shop.shop.controller;


import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.shop.entity.request.GetFansListRequest;
import com.shuaibi.shop.shop.service.IPmsShopFansService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 店铺粉丝表 前端控制器
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-04
 */
@RestController
@RequestMapping("/fans")
@Api(tags = "店铺粉丝Api")
public class PmsShopFansController {
    @Autowired
    IPmsShopFansService pmsShopFansService;

    @GetMapping()
    @ApiOperation("获取粉丝列表")
    public CommonResult getFansList(GetFansListRequest request){
        return CommonResult.success(pmsShopFansService.getFansList(request));
    }
}
