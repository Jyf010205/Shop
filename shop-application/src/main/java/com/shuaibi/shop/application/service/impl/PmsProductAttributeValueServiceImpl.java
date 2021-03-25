package com.shuaibi.shop.application.service.impl;

import com.shuaibi.shop.application.service.IPmsProductAttributeValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaibi.shop.common.entity.table.PmsProductAttributeValue;
import com.shuaibi.shop.common.mapper.PmsProductAttributeValueMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 存储产品参数数据表 服务实现类
 * </p>
 *
 * @author syq
 * @since 2021-02-04
 */
@Service
public class PmsProductAttributeValueServiceImpl extends ServiceImpl<PmsProductAttributeValueMapper, PmsProductAttributeValue> implements IPmsProductAttributeValueService {

    /**
     * 查询产品参数数据列表
     * @param productId
     * @return
     */
    @Override
    public List<PmsProductAttributeValue> getProductAttributeValueList(Long productId) {
        return this.getBaseMapper().getProductAttributeValueList(productId);
    }
}
