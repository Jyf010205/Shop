package com.shuaibi.shop.common.mapper;

import com.shuaibi.shop.common.entity.table.PmsProductAttributeValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 存储产品参数数据表 Mapper 接口
 * </p>
 *
 * @author syq
 * @since 2021-02-08
 */
public interface PmsProductAttributeValueMapper extends BaseMapper<PmsProductAttributeValue> {

    @Select("SELECT v.*,a.ATTRIBUTE_NAME FROM pms_product_attribute_value v LEFT JOIN pms_product_attribute a ON v.ATTRIBUTE_ID = a.ATTRIBUTE_ID WHERE PRODUCT_ID = #{productId}")
    List<PmsProductAttributeValue> getProductAttributeValueList(Long productId);
}
