package com.shuaibi.shop.shop.service.impl;

import com.shuaibi.shop.common.entity.table.PmsProductAttributeValue;
import com.shuaibi.shop.common.mapper.PmsProductAttributeValueMapper;
import com.shuaibi.shop.shop.service.IPmsProductAttributeValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 存储产品参数数据表 服务实现类
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-09
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
