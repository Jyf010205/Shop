package com.shuaibi.shop.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaibi.shop.application.service.IPmsFreightTemplateChargeService;
import com.shuaibi.shop.application.service.IPmsFreightTemplateFreeService;
import com.shuaibi.shop.application.service.IPmsFreightTemplateService;
import com.shuaibi.shop.common.entity.table.PmsFreightTemplate;
import com.shuaibi.shop.common.entity.table.PmsFreightTemplateCharge;
import com.shuaibi.shop.common.entity.table.PmsFreightTemplateFree;
import com.shuaibi.shop.common.mapper.PmsFreightTemplateMapper;
import com.shuaibi.shop.common.utils.Asserts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 运费模版表 服务实现类
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsFreightTemplateServiceImpl extends ServiceImpl<PmsFreightTemplateMapper, PmsFreightTemplate> implements IPmsFreightTemplateService {
    @Autowired
    private IPmsFreightTemplateFreeService pmsFreightTemplateFreeService;
    @Autowired
    private IPmsFreightTemplateChargeService pmsFreightTemplateChargeService;

    /**
     * 查询店铺运费模板
     * @param freightTemplateId
     * @return
     */
    @Override
    public PmsFreightTemplate getFreightTemplate(Long freightTemplateId) {
        PmsFreightTemplate pmsFreightTemplate = this.getOne(new LambdaQueryWrapper<PmsFreightTemplate>().eq(PmsFreightTemplate::getFreightTemplateId,freightTemplateId));
        if (pmsFreightTemplate == null) {
            Asserts.fail("查询失败");
        }
        pmsFreightTemplate.setFreightTemplateFreeList(pmsFreightTemplateFreeService.list(new LambdaQueryWrapper<PmsFreightTemplateFree>().eq(PmsFreightTemplateFree::getFreightTemplateId,pmsFreightTemplate.getFreightTemplateId())));
        pmsFreightTemplate.setFreightTemplateChargeList(pmsFreightTemplateChargeService.list(new LambdaQueryWrapper<PmsFreightTemplateCharge>().eq(PmsFreightTemplateCharge::getFreightTemplateId,pmsFreightTemplate.getFreightTemplateId())));
        return pmsFreightTemplate;
    }
}



