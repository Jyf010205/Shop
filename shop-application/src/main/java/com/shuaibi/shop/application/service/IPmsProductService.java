package com.shuaibi.shop.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shuaibi.shop.application.entity.request.GetProductRequest;
import com.shuaibi.shop.common.entity.table.PmsProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品信息表 服务类
 * </p>
 *
 * @author syq
 * @since 2021-02-04
 */
public interface IPmsProductService extends IService<PmsProduct> {


    IPage<PmsProduct> srarch(GetProductRequest pmsProduct);


}
