package com.shuaibi.shop.goods.controller;


import com.shuaibi.shop.common.entity.table.PmsProductManage;
import com.shuaibi.shop.goods.service.IPmsProductManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author syq
 * @since 2021-01-15
 */
@RestController
@RequestMapping("/product-manage")
@Api(tags = "PmsProductManageController", description = "商品管理")
@EnableSwagger2
public class PmsProductManageController {

    @Resource
    private IPmsProductManageService iPmsProductManageService;

    @ApiOperation(value = "商品查询")
    @GetMapping(value = "/search")
    public List<PmsProductManage>  searchProduct(@Valid PmsProductManage pmsProductManage){
        List<PmsProductManage> productManageList = iPmsProductManageService.searchProduct(pmsProductManage);
        return productManageList;
    }
}
