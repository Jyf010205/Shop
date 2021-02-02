package com.shuaibi.shop.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaibi.shop.common.entity.table.PmsFreightTemplate;
import com.shuaibi.shop.shop.entity.request.CreateFreightTemplateRequest;
import com.shuaibi.shop.shop.entity.request.GetFreightTemplateRequest;

/**
 * <p>
 * 运费模版表 服务类
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-01
 */
public interface IPmsFreightTemplateService extends IService<PmsFreightTemplate> {

    IPage<PmsFreightTemplate> getFreightTemplateList(GetFreightTemplateRequest request);

    PmsFreightTemplate getFreightTemplate(Long id);

    Boolean create(CreateFreightTemplateRequest request);

    boolean delete(Long id);
}
