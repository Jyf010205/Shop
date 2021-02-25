package com.shuaibi.shop.application.controller;


import cn.hutool.core.lang.tree.Tree;
import com.shuaibi.shop.application.service.IPmsProductCategoryService;
import com.shuaibi.shop.common.entity.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-25
 */
@RestController
@RequestMapping("/category")
@Api(tags = "商品类目Api")
public class PmsProductCategoryController {
    @Autowired
    private IPmsProductCategoryService pmsProductCategoryService;

    @ApiOperation("获取类目树")
    @GetMapping
    public CommonResult<List<Tree<Long>>> getCategoryTree(){
        return CommonResult.success(pmsProductCategoryService.getCategoryTree());
    }
}
