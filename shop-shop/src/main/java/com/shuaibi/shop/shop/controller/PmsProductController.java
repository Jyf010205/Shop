package com.shuaibi.shop.shop.controller;


import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.entity.table.PmsProduct;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.common.utils.EmptyUtil;
import com.shuaibi.shop.shop.entity.request.CreateProductRequest;
import com.shuaibi.shop.shop.entity.request.GetProductListRequest;
import com.shuaibi.shop.shop.entity.request.UpdateProductSkuRequest;
import com.shuaibi.shop.shop.entity.request.UpdateProductStatusRequest;
import com.shuaibi.shop.shop.service.IPmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    private IPmsProductService PmsProductService;

    @GetMapping
    @ApiOperation("查询商品列表")
    public CommonResult getProductList(@Valid GetProductListRequest request){
        return CommonResult.success(PmsProductService.getProductList(request));
    }

    @GetMapping("/{id}")
    @ApiOperation("查询商品详细信息")
    public CommonResult getProduct(@PathVariable Long id){
        PmsProduct product = PmsProductService.getProduct(id);
        if (EmptyUtil.isEmpty(product)){
            Asserts.fail("商品查询失败");
        }
        return CommonResult.success(product);
    }

    @PostMapping
    @ApiOperation("录入商品信息")
    public CommonResult create(@Valid @RequestBody CreateProductRequest request){
        Boolean status = PmsProductService.createPmsProduct(request);
        if (!status){
            Asserts.fail("商品录入失败");
        }
        return CommonResult.success(request,"商品录入成功");
    }

    @PutMapping("/status")
    @ApiOperation("修改商品状态")
    public CommonResult updateProductStatus(@Valid @RequestBody UpdateProductStatusRequest request){
        Boolean status = PmsProductService.updateProductStatus(request);
        if (!status){
            Asserts.fail("修改商品状态失败");
        }
        return CommonResult.success(request,"修改商品状态成功");
    }

    @PutMapping("/sku")
    @ApiOperation("修改商品Sku信息")
    public CommonResult updateProductSku(List<UpdateProductSkuRequest> request){
        Boolean status = PmsProductService.updateProductSku(request);
        if (!status){
            Asserts.fail("修改商品Sku失败");
        }
        return CommonResult.success(request,"修改商品Sku成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除商品信息")
    public CommonResult delete(@PathVariable Long id){
        Boolean status = PmsProductService.delete(id);
        if (!status){
            Asserts.fail("商品删除失败");
        }
        return CommonResult.success(id,"商品删除成功");
    }
}
