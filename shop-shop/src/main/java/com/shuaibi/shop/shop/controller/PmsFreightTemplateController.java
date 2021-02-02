package com.shuaibi.shop.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.entity.table.PmsFreightTemplate;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.shop.entity.request.CreateFreightTemplateRequest;
import com.shuaibi.shop.shop.entity.request.GetFreightTemplateRequest;
import com.shuaibi.shop.shop.service.IPmsFreightTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * <p>
 * 运费模版表 前端控制器
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-01
 */
@RestController
@RequestMapping("/freight")
@Api(tags = "店铺运费模板Api")
public class PmsFreightTemplateController {
    @Autowired
    IPmsFreightTemplateService pmsFreightTemplateService;

    @GetMapping
    @ApiOperation("查询店铺运费模板列表")
    public CommonResult<IPage<PmsFreightTemplate>> getFreightTemplateList(GetFreightTemplateRequest request){
        return CommonResult.success(pmsFreightTemplateService.getFreightTemplateList(request));
    }

    @GetMapping("/{id}")
    @ApiOperation("查询店铺运费模板")
    public CommonResult<PmsFreightTemplate> getFreightTemplate(@PathVariable Long id){
        Optional<PmsFreightTemplate> pmsFreightTemplate = Optional.ofNullable(pmsFreightTemplateService.getFreightTemplate(id));
        if (!pmsFreightTemplate.isPresent()) {
            Asserts.fail("查询失败");
        }
        return CommonResult.success(pmsFreightTemplate.get(),"查询成功");
    }

    @PostMapping
    @ApiOperation("创建店铺运费模板")
    public CommonResult create(@Valid @RequestBody CreateFreightTemplateRequest request){
        Boolean status = pmsFreightTemplateService.create(request);
        if (!status){
            Asserts.fail("创建店铺运费模板失败");
        }
        return CommonResult.success(request,"创建店铺运费模板成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除店铺运费模板")
    public CommonResult delete(@PathVariable Long id){
        boolean status = pmsFreightTemplateService.delete(id);
        if (!status){
            Asserts.fail("删除失败");
        }
        return CommonResult.success(id,"删除成功");
    }
}
