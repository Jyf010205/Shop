package com.shuaibi.shop.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaibi.shop.common.entity.table.PmsProduct;
import com.shuaibi.shop.shop.entity.request.CreateProductRequest;

/**
 * <p>
 * 商品信息表 服务类
 * </p>
 *
 * @author syq
 * @since 2021-02-04
 */
public interface IPmsProductService extends IService<PmsProduct> {

    Boolean createPmsProduct(CreateProductRequest createProductRequest);

}
