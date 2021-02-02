package com.shuaibi.shop.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaibi.shop.common.entity.table.PmsFreightTemplate;
import com.shuaibi.shop.common.entity.table.PmsFreightTemplateCharge;
import com.shuaibi.shop.common.entity.table.PmsFreightTemplateFree;
import com.shuaibi.shop.common.entity.table.PmsShop;
import com.shuaibi.shop.common.mapper.PmsFreightTemplateChargeMapper;
import com.shuaibi.shop.common.mapper.PmsFreightTemplateFreeMapper;
import com.shuaibi.shop.common.mapper.PmsFreightTemplateMapper;
import com.shuaibi.shop.common.mapper.PmsShopMapper;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.common.utils.SnowflakeIdWorker;
import com.shuaibi.shop.shop.entity.request.CreateFreightTemplateRequest;
import com.shuaibi.shop.shop.entity.request.GetFreightTemplateRequest;
import com.shuaibi.shop.shop.service.IPmsFreightTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private PmsFreightTemplateFreeMapper freightTemplateFreeMapper;
    @Autowired
    private PmsFreightTemplateChargeMapper freightTemplateChargeMapper;


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
            pmsFreightTemplate.setFreightTemplateFreeList(freightTemplateFreeMapper.selectList(new LambdaQueryWrapper<PmsFreightTemplateFree>().eq(PmsFreightTemplateFree::getFreightTemplateId,pmsFreightTemplate.getFreightTemplateId())));
            pmsFreightTemplate.setFreightTemplateChargeList(freightTemplateChargeMapper.selectList(new LambdaQueryWrapper<PmsFreightTemplateCharge>().eq(PmsFreightTemplateCharge::getFreightTemplateId,pmsFreightTemplate.getFreightTemplateId())));
        });
        return freightTemplatePage;
    }

    /**
     * 查询店铺运费模板
     * @param id
     * @return
     */
    @Override
    public PmsFreightTemplate getFreightTemplate(Long id) {
        PmsFreightTemplate pmsFreightTemplate = this.getById(id);
        if (pmsFreightTemplate == null) {
            Asserts.fail("查询失败");
        }
        pmsFreightTemplate.setFreightTemplateFreeList(freightTemplateFreeMapper.selectList(new LambdaQueryWrapper<PmsFreightTemplateFree>().eq(PmsFreightTemplateFree::getFreightTemplateId,pmsFreightTemplate.getFreightTemplateId())));
        pmsFreightTemplate.setFreightTemplateChargeList(freightTemplateChargeMapper.selectList(new LambdaQueryWrapper<PmsFreightTemplateCharge>().eq(PmsFreightTemplateCharge::getFreightTemplateId,pmsFreightTemplate.getFreightTemplateId())));
        return pmsFreightTemplate;
    }

    /**
     * 创建店铺运费模板
     * @param request
     * @return
     */
    @Override
    public Boolean create(CreateFreightTemplateRequest request) {
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
                    freightTemplateFreeMapper.insert(pmsFreightTemplateFree);
                });
                break;
            case NOT_FREE:
                //不包邮规则存入数据库
                List<PmsFreightTemplateCharge> freightTemplateChargeList = request.getFreightTemplateChargeList();
                freightTemplateChargeList.forEach(pmsFreightTemplateCharge -> {
                    pmsFreightTemplateCharge.setFreightTemplateId(freightTemplateId);
                    freightTemplateChargeMapper.insert(pmsFreightTemplateCharge);
                });
                break;
        }
        PmsFreightTemplate pmsFreightTemplate = new PmsFreightTemplate();
        pmsFreightTemplate.setFreightTemplateId(freightTemplateId);
        BeanUtils.copyProperties(request,pmsFreightTemplate);
        return this.save(pmsFreightTemplate);
    }

    /**
     * 删除店铺运费模板
     * @param id
     * @return
     */
    @Override
    public boolean delete(Long id) {
        PmsFreightTemplate pmsFreightTemplate = this.getById(id);
        //删除关联表
        freightTemplateFreeMapper.delete(new LambdaQueryWrapper<PmsFreightTemplateFree>().eq(PmsFreightTemplateFree::getFreightTemplateId, pmsFreightTemplate.getFreightTemplateId()));
        freightTemplateChargeMapper.delete(new LambdaQueryWrapper<PmsFreightTemplateCharge>().eq(PmsFreightTemplateCharge::getFreightTemplateId,pmsFreightTemplate.getFreightTemplateId()));
        return this.removeById(id);
    }
}
