package com.shuaibi.shop.shop.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaibi.shop.common.entity.table.PmsProduct;
import com.shuaibi.shop.common.entity.table.PmsProductAttributeValue;
import com.shuaibi.shop.common.entity.table.PmsProductSku;
import com.shuaibi.shop.common.mapper.PmsProductMapper;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.common.utils.SnowflakeIdWorker;
import com.shuaibi.shop.shop.entity.request.CreateProductAttributeValueRequest;
import com.shuaibi.shop.shop.entity.request.CreateProductRequest;
import com.shuaibi.shop.shop.entity.request.CreateProductSkuRequest;
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
                //Attribute转为{"key":"value"}
                JSONObject skuDataJson = new JSONObject();
                productSkuRequest.getSkuAttributeValueList().forEach(skuAttributeValue -> skuDataJson.set(skuAttributeValue.getAttributeName(),skuAttributeValue.getAttributeValue()));

                productSku.setProductId(productId);
                productSku.setSkuId(productSkuSnowflakeIdWorker.nextId());
                productSku.setSkuData(JSONUtil.toJsonStr(skuDataJson));
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

}
