package com.shuaibi.shop.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaibi.shop.common.entity.table.PmsProduct;
import com.shuaibi.shop.common.mapper.PmsProductAttributeValueMapper;
import com.shuaibi.shop.common.mapper.PmsProductMapper;
import com.shuaibi.shop.common.mapper.PmsProductSkuMapper;
import com.shuaibi.shop.common.utils.EmptyUtil;
import com.shuaibi.shop.common.utils.SnowflakeIdWorker;
import com.shuaibi.shop.shop.entity.request.InputProduct;
import com.shuaibi.shop.shop.service.IPmsProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static cn.hutool.core.lang.Console.log;

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
    @Qualifier("productSnowflakeIdWorker")
    private SnowflakeIdWorker productSnowflakeIdWorker;

    @Autowired
    PmsProductAttributeValueMapper pmsProductAttributeValueMapper;

    @Autowired
    PmsProductSkuMapper pmsProductSkuMapper;

    @Override
    public void inputProduct(InputProduct inputProduct){
        PmsProduct pmsProduct = new PmsProduct();
        long freightSnowflakeId = productSnowflakeIdWorker.nextId();
        try {
            inputProduct.setProductId(freightSnowflakeId);
            BeanUtils.copyProperties(inputProduct,pmsProduct);
            pmsProductMapper.insert(pmsProduct);
            inputProduct.getPmsProductAttributeValueList().forEach(pmsProductAttributeValue -> {
                pmsProductAttributeValue.setProductId(inputProduct.getProductId());
                pmsProductAttributeValueMapper.insert(pmsProductAttributeValue);
            });
            if(EmptyUtil.isEmpty(inputProduct.getPmsProductSkuList())){
                inputProduct.getPmsProductSkuList().forEach(pmsProductSku -> {
                    pmsProductSkuMapper.insert(pmsProductSku);
                });
            }


        }catch (Exception e){
            log(e,"商品导入出错");
        }

    }

}
