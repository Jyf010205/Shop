package com.shuaibi.shop.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shuaibi.shop.application.entity.request.GetProductAttribute;
import com.shuaibi.shop.application.service.IPmsProductAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaibi.shop.common.entity.table.PmsProductAttribute;
import com.shuaibi.shop.common.mapper.PmsProductAttributeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品参数表 服务实现类
 * </p>
 *
 * @author syq
 * @since 2021-02-04
 */
@Service
public class PmsProductAttributeServiceImpl extends ServiceImpl<PmsProductAttributeMapper, PmsProductAttribute> implements IPmsProductAttributeService {

    @Autowired
    PmsProductAttributeMapper pmsProductAttributeMapper;

    @Override
    public List<PmsProductAttribute> getAttribute(GetProductAttribute getProductAttribute){
        List<PmsProductAttribute> pmsProductAttributeList = pmsProductAttributeMapper.selectList(new LambdaQueryWrapper<PmsProductAttribute>()
                .eq(PmsProductAttribute::getCategoryId,getProductAttribute.getCategoryId()));
        return pmsProductAttributeList;
    }
}
