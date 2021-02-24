package com.shuaibi.shop.shop.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaibi.shop.common.entity.table.PmsProduct;
import com.shuaibi.shop.common.entity.table.PmsProductAttributeValue;
import com.shuaibi.shop.common.entity.table.PmsProductSku;
import com.shuaibi.shop.common.mapper.PmsProductMapper;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.common.utils.EmptyUtil;
import com.shuaibi.shop.common.utils.SnowflakeIdWorker;
import com.shuaibi.shop.shop.entity.request.*;
import com.shuaibi.shop.shop.service.IPmsProductAttributeValueService;
import com.shuaibi.shop.shop.service.IPmsProductService;
import com.shuaibi.shop.shop.service.IPmsProductSkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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
@Transactional(rollbackFor = Exception.class)
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {
    @Autowired
    @Qualifier("productSnowflakeIdWorker")
    private SnowflakeIdWorker productSnowflakeIdWorker;
    @Autowired
    @Qualifier("productSkuSnowflakeIdWorker")
    private SnowflakeIdWorker productSkuSnowflakeIdWorker;
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
        return this.page(page, new LambdaQueryWrapper<PmsProduct>()
                .eq(PmsProduct::getShopId, request.getShopId())
                .eq(PmsProduct::getDeleteStatus,false)
                .eq(Optional.ofNullable(request.getPublishStatus()).isPresent(),PmsProduct::getPublishStatus,request.getPublishStatus())
                .eq(Optional.ofNullable(request.getProductCategoryId()).isPresent(),PmsProduct::getProductCategoryId,request.getProductCategoryId()));
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

    /**
     * 录入商品信息
     * @param createProductRequest
     */
    @Override
    public Boolean createPmsProduct(CreateProductRequest createProductRequest){
        long productId = productSnowflakeIdWorker.nextId();

        //添加属性列表
        List<CreateProductAttributeValueRequest> productAttributeValueRequestList = createProductRequest.getCreateProductAttributeValueRequestList();
        List<PmsProductAttributeValue> productAttributeValueList = new ArrayList<>();
        productAttributeValueRequestList.forEach(productAttributeValueRequest -> {
            PmsProductAttributeValue productAttributeValue = new PmsProductAttributeValue();
            BeanUtils.copyProperties(productAttributeValueRequest,productAttributeValue);
            productAttributeValue.setProductId(productId);
            productAttributeValueList.add(productAttributeValue);
        });
        boolean attributeValueStatus = pmsProductAttributeValueService.saveBatch(productAttributeValueList);
        if (!attributeValueStatus){
            Asserts.fail("商品属性录入失败");
        }

        AtomicReference<Integer> stock = new AtomicReference<>(0);
        //添加Sku列表
        List<CreateProductSkuRequest> productSkuRequestList = createProductRequest.getCreateProductSkuRequestList();
        if (productSkuRequestList != null){
            List<PmsProductSku> productSkuList = new ArrayList<>();
            productSkuRequestList.forEach(productSkuRequest -> {
                PmsProductSku productSku = new PmsProductSku();
                BeanUtils.copyProperties(productSkuRequest,productSku);
                productSku.setProductId(productId);
                productSku.setSkuId(productSkuSnowflakeIdWorker.nextId());
                productSku.setSkuData(JSONUtil.toJsonStr(productSkuRequest.getSkuAttributeValueList()));
                //总库存等于Sku列表中的总库存
                stock.updateAndGet(v -> v + productSku.getSkuStock());

                productSkuList.add(productSku);
            });
            boolean skuStatus = pmsProductSkuService.saveBatch(productSkuList);
            if (!skuStatus){
                Asserts.fail("商品Sku录入失败");
            }
        }

        PmsProduct pmsProduct = new PmsProduct();
        BeanUtils.copyProperties(createProductRequest,pmsProduct);
        pmsProduct.setProductId(productId);
        if (stock.get() != 0){
            pmsProduct.setStock(stock.get());
        }
        return this.save(pmsProduct);
    }

    /**
     * 修改商品状态
     * @param request
     * @return
     */
    @Override
    public Boolean updateProductStatus(UpdateProductStatusRequest request) {
        return this.update(new LambdaUpdateWrapper<PmsProduct>()
                .set(Optional.ofNullable(request.getPublishStatus()).isPresent(),PmsProduct::getPublishStatus,request.getPublishStatus())
                .set(Optional.ofNullable(request.getNewStatus()).isPresent(),PmsProduct::getNewStatus,request.getNewStatus())
                .eq(PmsProduct::getId,request.getId()));
    }

    /**
     * 修改商品Sku信息
     * @param request
     * @return
     */
    @Override
    public Boolean updateProductSku(List<UpdateProductSkuRequest> request) {
        boolean allStatus = true;
        for (UpdateProductSkuRequest productSku:request) {
            PmsProductSku pmsProductSku = new PmsProductSku();
            BeanUtils.copyProperties(pmsProductSku,productSku);
            boolean status = pmsProductSkuService.updateById(pmsProductSku);
            allStatus = allStatus && status;
        }
        return allStatus;
    }

    /**
     * 删除商品信息
     * @param id
     * @return
     */
    @Override
    public Boolean delete(Long id) {
        return update(new LambdaQueryWrapper<PmsProduct>().eq(PmsProduct::getDeleteStatus,true));
    }

}
