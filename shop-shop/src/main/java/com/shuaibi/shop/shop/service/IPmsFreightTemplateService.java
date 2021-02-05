package com.shuaibi.shop.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaibi.shop.common.entity.table.PmsFreightTemplate;
import com.shuaibi.shop.shop.entity.request.CreateFreightTemplateRequest;
import com.shuaibi.shop.shop.entity.request.GetFreightTemplateRequest;
import com.shuaibi.shop.shop.entity.request.UpdateFreightTemplateRequest;

import java.util.Optional;

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

    Optional<PmsFreightTemplate> getFreightTemplate(Long id);

    Boolean createTemplate(CreateFreightTemplateRequest request);

    Boolean updateTemplate(UpdateFreightTemplateRequest request);

    Boolean deleteTemplate(Long id);
}
