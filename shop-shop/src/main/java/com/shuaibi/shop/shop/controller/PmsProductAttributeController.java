package com.shuaibi.shop.shop.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.entity.table.PmsProductAttribute;
import com.shuaibi.shop.shop.entity.request.GetProductAttributeRequest;
import com.shuaibi.shop.shop.service.IPmsProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 产品参数表 前端控制器
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-09
 */
@RestController
@RequestMapping("/productAttribute")
@Api(tags = "商品属性Api")
public class PmsProductAttributeController {
    @Autowired
    private IPmsProductAttributeService pmsProductAttributeService;

    @GetMapping
    @ApiOperation("查询相关类目的属性列表")
    public CommonResult<List<PmsProductAttribute>> getProductAttributes(GetProductAttributeRequest request){
        return CommonResult.success(pmsProductAttributeService.list(new LambdaQueryWrapper<PmsProductAttribute>()
                .eq(PmsProductAttribute::getCategoryId,request.getCategoryId())
                .eq(PmsProductAttribute::getType,request.getType())));
    }
}
