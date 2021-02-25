package com.shuaibi.shop.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaibi.shop.application.entity.request.GetProductListRequest;
import com.shuaibi.shop.common.entity.table.PmsProduct;

/**
 * <p>
 * 商品信息表 服务类
 * </p>
 *
 * @author syq
 * @since 2021-02-04
 */
public interface IPmsProductService extends IService<PmsProduct> {
    IPage<PmsProduct> getProductList(GetProductListRequest request);
}
