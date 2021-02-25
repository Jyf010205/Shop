package com.shuaibi.shop.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaibi.shop.application.entity.request.GetProductListRequest;
import com.shuaibi.shop.application.service.IPmsProductService;
import com.shuaibi.shop.common.entity.table.PmsProduct;
import com.shuaibi.shop.common.mapper.PmsProductMapper;
import lombok.extern.slf4j.Slf4j;
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

    /**
     * 查询商品列表
     * @param request
     * @return
     */
    @Override
    public IPage<PmsProduct> getProductList(GetProductListRequest request) {
        return null;
    }
}
