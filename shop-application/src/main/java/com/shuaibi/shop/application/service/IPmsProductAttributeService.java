package com.shuaibi.shop.application.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaibi.shop.application.entity.request.GetProductAttribute;
import com.shuaibi.shop.common.entity.table.PmsProductAttribute;

import java.util.List;

/**
 * <p>
 * 产品参数表 服务类
 * </p>
 *
 * @author syq
 * @since 2021-02-04
 */
public interface IPmsProductAttributeService extends IService<PmsProductAttribute> {

    List<PmsProductAttribute> getAttribute(GetProductAttribute productAttribute);
}
