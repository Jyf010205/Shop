package com.shuaibi.shop.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaibi.shop.application.entity.request.GetProductListRequest;
import com.shuaibi.shop.application.service.IPmsProductAttributeValueService;
import com.shuaibi.shop.application.service.IPmsProductCategoryService;
import com.shuaibi.shop.application.service.IPmsProductService;
import com.shuaibi.shop.application.service.IPmsProductSkuService;
import com.shuaibi.shop.common.entity.table.PmsProduct;
import com.shuaibi.shop.common.entity.table.PmsProductAttributeValue;
import com.shuaibi.shop.common.entity.table.PmsProductCategory;
import com.shuaibi.shop.common.entity.table.PmsProductSku;
import com.shuaibi.shop.common.mapper.PmsProductMapper;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.common.utils.EmptyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author syq
 * @since 2021-02-04
 */
@Slf4j
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {
    @Autowired
    private IPmsProductCategoryService pmsProductCategoryService;
    @Autowired
    private IPmsProductSkuService pmsProductSkuService;
    @Autowired
    private IPmsProductAttributeValueService pmsProductAttributeValueService;
    /**
     * 查询商品列表
     * @param request
     * @return
     */
    @Override
    public IPage<PmsProduct> getProductList(GetProductListRequest request) {
        IPage<PmsProduct> page = new Page<>(request.getPageNum(), request.getPageSize());
        List<Long> categoryIds = new ArrayList<>();
        //查询类目及其子类目
        if (!EmptyUtil.isEmpty(request.getProductCategoryId())){
            categoryIds = pmsProductCategoryService.list(new LambdaQueryWrapper<PmsProductCategory>()
                    .eq(PmsProductCategory::getParentsId, request.getProductCategoryId()))
                    .stream().map(PmsProductCategory::getCategoryId).collect(Collectors.toList());
            categoryIds.add(request.getProductCategoryId());
        }
        LambdaQueryWrapper<PmsProduct> queryWrapper = new LambdaQueryWrapper<PmsProduct>()
                .eq(PmsProduct::getDeleteStatus, false)
                .eq(!EmptyUtil.isEmpty(request.getShopId()), PmsProduct::getShopId, request.getShopId())
                .in(!EmptyUtil.isEmpty(request.getProductCategoryId()), PmsProduct::getProductCategoryId, categoryIds)
                .like(!EmptyUtil.isEmpty(request.getQueryIndex()), PmsProduct::getQueryIndex, request.getQueryIndex());
        if (!EmptyUtil.isEmpty(request.getSales())){
            queryWrapper.orderBy(true,request.getSales(),PmsProduct::getSales);
        }
        if (!EmptyUtil.isEmpty(request.getPrice())){
            queryWrapper.orderBy(true,request.getPrice(),PmsProduct::getPrice);
        }
        return this.page(page,queryWrapper);
    }

    /**
     * 查询商品详细信息
     * @param id
     */
    @Override
    public PmsProduct getProduct(Long id) {
        PmsProduct pmsProduct = this.getById(id);
        if (EmptyUtil.isEmpty(pmsProduct) || pmsProduct.getDeleteStatus()){
            Asserts.fail("商品id不存在");
        }
        List<PmsProductAttributeValue> productAttributeValueList = pmsProductAttributeValueService.getProductAttributeValueList(pmsProduct.getProductId());
        List<PmsProductSku> productSkuList = pmsProductSkuService.list(new LambdaQueryWrapper<PmsProductSku>().eq(PmsProductSku::getProductId, pmsProduct.getProductId()));
        pmsProduct.setProductAttributeValueList(productAttributeValueList);
        pmsProduct.setProductSkuList(productSkuList);
        return pmsProduct;
    }
}
