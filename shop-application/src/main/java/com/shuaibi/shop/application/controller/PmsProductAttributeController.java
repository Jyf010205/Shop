package com.shuaibi.shop.application.controller;


import com.shuaibi.shop.application.entity.request.GetProductAttribute;
import com.shuaibi.shop.application.service.IPmsProductAttributeService;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.entity.table.PmsProductAttribute;
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
 * @author syq
 * @since 2021-02-08
 */
@RestController
@RequestMapping("/pms-product-attribute")
@Api(tags = "商品类目")
public class PmsProductAttributeController {

    @Autowired
    IPmsProductAttributeService pmsProductAttributeService;

    @GetMapping
    @ApiOperation("查询商品类目属性信息")
    public CommonResult<List<PmsProductAttribute>>getAttribute(GetProductAttribute productAttribute){
        //根据categoryId获取pms_product_attribute表数据
        return CommonResult.success(pmsProductAttributeService.getAttribute(productAttribute),"查询商品类目属性信息成功");
    }
}
