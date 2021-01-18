package com.shuaibi.shop.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shuaibi.shop.common.entity.table.PmsProductManage;
import com.shuaibi.shop.common.mapper.PmsProductManageMapper;
import com.shuaibi.shop.goods.service.IPmsProductManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ParameterMetaData;
import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author syq
 * @since 2021-01-15
 */
@Service
public class PmsProductManageServiceImpl extends ServiceImpl<PmsProductManageMapper, PmsProductManage> implements IPmsProductManageService {

    @Autowired
    private PmsProductManageMapper pmsProductManageMapper;

    public List<PmsProductManage> searchProduct(PmsProductManage pmsProductManage){
        QueryWrapper<PmsProductManage> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(pmsProductManage.getProductId())){
            queryWrapper.eq("PRODUCT_ID",pmsProductManage.getProductId());
        }
        if (StringUtils.isNotEmpty(pmsProductManage.getProduceName())){
            queryWrapper.eq("PRODUCT_NAME",pmsProductManage.getProduceName());
        }
        List<PmsProductManage> productManageList = pmsProductManageMapper.selectList(queryWrapper);
        return productManageList;
    }
}
