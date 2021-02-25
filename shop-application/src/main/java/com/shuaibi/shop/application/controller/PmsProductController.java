package com.shuaibi.shop.application.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shuaibi.shop.application.entity.request.GetProductListRequest;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.entity.table.PmsProduct;
import com.shuaibi.shop.application.service.IPmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author syq
 * @since 2021-02-04
 */
@RestController
@RequestMapping("/product")
@Api(tags = "商品概览")
public class PmsProductController {

    @Autowired
    IPmsProductService PmsProductService;

    @GetMapping
    @ApiOperation("查询商品列表")
    public CommonResult<IPage<PmsProduct>> getProductList(GetProductListRequest request){
        return CommonResult.success(PmsProductService.getProductList(request),"商品信息查询成功");
    }

}
