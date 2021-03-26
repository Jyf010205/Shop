package com.shuaibi.shop.application.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaibi.shop.common.entity.table.PmsFreightTemplate;

/**
 * <p>
 * 运费模版表 服务类
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-01
 */
public interface IPmsFreightTemplateService extends IService<PmsFreightTemplate> {
    PmsFreightTemplate getFreightTemplate(Long freightTemplateId);
}
