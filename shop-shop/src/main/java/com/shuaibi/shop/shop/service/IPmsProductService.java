package com.shuaibi.shop.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaibi.shop.common.entity.table.PmsProduct;
import com.shuaibi.shop.shop.entity.request.CreateProductRequest;
import com.shuaibi.shop.shop.entity.request.GetProductListRequest;
import com.shuaibi.shop.shop.entity.request.UpdateProductSkuRequest;
import com.shuaibi.shop.shop.entity.request.UpdateProductStatusRequest;

import java.util.List;

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

    PmsProduct getProduct(Long id);

    Boolean createPmsProduct(CreateProductRequest createProductRequest);

    Boolean updateProductStatus(UpdateProductStatusRequest request);

    Boolean updateProductSku(List<UpdateProductSkuRequest> request);

    Boolean delete(Long id);
}
