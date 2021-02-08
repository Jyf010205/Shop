package com.shuaibi.shop.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaibi.shop.application.entity.request.GetProductRequest;
import com.shuaibi.shop.application.service.IPmsProductService;
import com.shuaibi.shop.common.entity.table.PmsProduct;
import com.shuaibi.shop.common.mapper.PmsProductAttributeValueMapper;
import com.shuaibi.shop.common.mapper.PmsProductMapper;
import com.shuaibi.shop.common.mapper.PmsProductSkuMapper;
import com.shuaibi.shop.common.utils.EmptyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    IPmsProductService pmsProductService;

    @Autowired
    PmsProductMapper pmsProductMapper;


    @Autowired
    PmsProductAttributeValueMapper pmsProductAttributeValueMapper;

    @Autowired
    PmsProductSkuMapper pmsProductSkuMapper;

    @Override
    public IPage<PmsProduct> srarch(GetProductRequest getProductRequest){
        IPage<PmsProduct> page = new Page<>(getProductRequest.getPageNum(),getProductRequest.getPageSize());
        IPage<PmsProduct> pmsProductIPage = null;
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!EmptyUtil.isEmpty(getProductRequest.getProductId())){
            queryWrapper.eq("PRODUCT_ID",getProductRequest.getProductId());
        }
        pmsProductIPage = this.page(page,queryWrapper);

        return pmsProductIPage;
    }

}
