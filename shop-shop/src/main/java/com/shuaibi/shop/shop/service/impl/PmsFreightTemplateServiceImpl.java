package com.shuaibi.shop.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaibi.shop.common.entity.table.PmsFreightTemplate;
import com.shuaibi.shop.common.entity.table.PmsFreightTemplateCharge;
import com.shuaibi.shop.common.entity.table.PmsFreightTemplateFree;
import com.shuaibi.shop.common.entity.table.PmsShop;
import com.shuaibi.shop.common.mapper.PmsFreightTemplateMapper;
import com.shuaibi.shop.common.mapper.PmsShopMapper;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.common.utils.SnowflakeIdWorker;
import com.shuaibi.shop.shop.entity.request.CreateFreightTemplateRequest;
import com.shuaibi.shop.shop.entity.request.GetFreightTemplateRequest;
import com.shuaibi.shop.shop.entity.request.UpdateFreightTemplateRequest;
import com.shuaibi.shop.shop.service.IPmsFreightTemplateChargeService;
import com.shuaibi.shop.shop.service.IPmsFreightTemplateFreeService;
import com.shuaibi.shop.shop.service.IPmsFreightTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private PmsShopMapper pmsShopMapper;
    @Autowired
    @Qualifier("freightSnowflakeIdWorker")
    private SnowflakeIdWorker freightSnowflakeIdWorker;
    @Autowired
    private IPmsFreightTemplateFreeService pmsFreightTemplateFreeService;
    @Autowired
    private IPmsFreightTemplateChargeService pmsFreightTemplateChargeService;


    /**
     * 查询店铺运费模板
     * @param request
     */
    @Override
    public IPage<PmsFreightTemplate> getFreightTemplateList(GetFreightTemplateRequest request) {
        IPage<PmsFreightTemplate> page = new Page<>(request.getPageNum(), request.getPageSize());
        IPage<PmsFreightTemplate> freightTemplatePage = this.page(page, new LambdaQueryWrapper<PmsFreightTemplate>().eq(PmsFreightTemplate::getShopId, request.getShopId()));
        //根据templateId查询对应的包邮、不包邮规则
        freightTemplatePage.getRecords().forEach(pmsFreightTemplate -> {
            pmsFreightTemplate.setFreightTemplateFreeList(pmsFreightTemplateFreeService.list(new LambdaQueryWrapper<PmsFreightTemplateFree>().eq(PmsFreightTemplateFree::getFreightTemplateId,pmsFreightTemplate.getFreightTemplateId())));
            pmsFreightTemplate.setFreightTemplateChargeList(pmsFreightTemplateChargeService.list(new LambdaQueryWrapper<PmsFreightTemplateCharge>().eq(PmsFreightTemplateCharge::getFreightTemplateId,pmsFreightTemplate.getFreightTemplateId())));
        });
        return freightTemplatePage;
    }

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

    /**
     * 创建店铺运费模板
     * @param request
     * @return
     */
    @Override
    public Boolean createTemplate(CreateFreightTemplateRequest request) {
        if (pmsShopMapper.selectOne(new LambdaQueryWrapper<PmsShop>().eq(PmsShop::getShopId,request.getShopId())) == null){
            Asserts.fail("ShopID不正确");
        }
        long freightTemplateId = freightSnowflakeIdWorker.nextId();
        switch (request.getTemplateType()){
            case CONDITION_FREE:
                //条件包邮规则存入数据库
                List<PmsFreightTemplateFree> freightTemplateFreeList = request.getFreightTemplateFreeList();
                freightTemplateFreeList.forEach(pmsFreightTemplateFree -> {
                    pmsFreightTemplateFree.setFreightTemplateId(freightTemplateId);
                    pmsFreightTemplateFreeService.save((pmsFreightTemplateFree));
                });
                break;
            case NOT_FREE:
                //不包邮规则存入数据库
                List<PmsFreightTemplateCharge> freightTemplateChargeList = request.getFreightTemplateChargeList();
                freightTemplateChargeList.forEach(pmsFreightTemplateCharge -> {
                    pmsFreightTemplateCharge.setFreightTemplateId(freightTemplateId);
                    pmsFreightTemplateChargeService.save(pmsFreightTemplateCharge);
                });
                break;
        }
        PmsFreightTemplate pmsFreightTemplate = new PmsFreightTemplate();
        pmsFreightTemplate.setFreightTemplateId(freightTemplateId);
        BeanUtils.copyProperties(request,pmsFreightTemplate);
        return this.save(pmsFreightTemplate);
    }

    /**
     *
     * @param request
     * @return
     */
    @Override
    public Boolean updateTemplate(UpdateFreightTemplateRequest request) {
        switch (request.getTemplateType()){
            case FREE:
                //删除条件包邮、不包邮的规则
                pmsFreightTemplateFreeService.remove(new LambdaQueryWrapper<PmsFreightTemplateFree>().eq(PmsFreightTemplateFree::getFreightTemplateId, request.getFreightTemplateId()));
                pmsFreightTemplateChargeService.remove(new LambdaQueryWrapper<PmsFreightTemplateCharge>().eq(PmsFreightTemplateCharge::getFreightTemplateId,request.getFreightTemplateId()));
                break;
            case CONDITION_FREE:
                //删除不包邮的规则
                pmsFreightTemplateChargeService.remove(new LambdaQueryWrapper<PmsFreightTemplateCharge>().eq(PmsFreightTemplateCharge::getFreightTemplateId,request.getFreightTemplateId()));
                List<PmsFreightTemplateFree> freightTemplateFreeList = request.getFreightTemplateFreeList();
                //保留规则的Ids(去空)
                List<Long> freeSaveListIds = freightTemplateFreeList.stream().map(PmsFreightTemplateFree::getId).filter(Objects::nonNull).collect(Collectors.toList());
                //差量修改规则
                List<Long> freeRemoveListIds = pmsFreightTemplateFreeService.list(new LambdaQueryWrapper<PmsFreightTemplateFree>().eq(PmsFreightTemplateFree::getFreightTemplateId, request.getFreightTemplateId())).stream()
                        .map(PmsFreightTemplateFree::getId).filter(id -> !freeSaveListIds.contains(id)).collect(Collectors.toList());
                pmsFreightTemplateFreeService.removeByIds(freeRemoveListIds);
                pmsFreightTemplateFreeService.saveOrUpdateBatch(freightTemplateFreeList);
                break;
            case NOT_FREE:
                //删除条件包邮的规则
                pmsFreightTemplateFreeService.remove(new LambdaQueryWrapper<PmsFreightTemplateFree>().eq(PmsFreightTemplateFree::getFreightTemplateId, request.getFreightTemplateId()));
                List<PmsFreightTemplateCharge> freightTemplateChargeList = request.getFreightTemplateChargeList();
                //保留规则的Ids(去空)
                List<Long> chargeSaveListIds = freightTemplateChargeList.stream().map(PmsFreightTemplateCharge::getId).filter(Objects::nonNull).collect(Collectors.toList());
                //差量修改规则
                List<Long> chargeRemoveListIds = pmsFreightTemplateChargeService.list(new LambdaQueryWrapper<PmsFreightTemplateCharge>().eq(PmsFreightTemplateCharge::getFreightTemplateId, request.getFreightTemplateId())).stream()
                        .map(PmsFreightTemplateCharge::getId).filter(id -> !chargeSaveListIds.contains(id)).collect(Collectors.toList());
                pmsFreightTemplateChargeService.removeByIds(chargeRemoveListIds);
                pmsFreightTemplateChargeService.saveOrUpdateBatch(freightTemplateChargeList);
                break;
        }
        PmsFreightTemplate pmsFreightTemplate = new PmsFreightTemplate();
        BeanUtils.copyProperties(request,pmsFreightTemplate);
        return this.updateById(pmsFreightTemplate);
    }

    /**
     * 删除店铺运费模板
     * @param id
     * @return
     */
    @Override
    public Boolean deleteTemplate(Long id) {
        PmsFreightTemplate pmsFreightTemplate = this.getById(id);
        if (!Optional.ofNullable(pmsFreightTemplate).isPresent()){
            Asserts.fail("运费模板不存在");
        }
        //删除关联表
        pmsFreightTemplateFreeService.remove(new LambdaQueryWrapper<PmsFreightTemplateFree>().eq(PmsFreightTemplateFree::getFreightTemplateId, pmsFreightTemplate.getFreightTemplateId()));
        pmsFreightTemplateChargeService.remove(new LambdaQueryWrapper<PmsFreightTemplateCharge>().eq(PmsFreightTemplateCharge::getFreightTemplateId,pmsFreightTemplate.getFreightTemplateId()));
        return this.removeById(id);
    }
}



