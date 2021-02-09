package com.shuaibi.shop.shop.controller;


import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.shop.entity.request.CreateProductRequest;
import com.shuaibi.shop.shop.service.IPmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
@Api(tags = "商品Api")
public class PmsProductController {

    @Autowired
    IPmsProductService PmsProductService;

    @PostMapping
    @ApiOperation("录入商品信息")
    public CommonResult create(@Valid @RequestBody CreateProductRequest request){
        Boolean status = PmsProductService.createPmsProduct(request);
        if (!status){
            Asserts.fail("商品录入失败");
        }
        return CommonResult.success(request,"商品录入成功");
    }

}
