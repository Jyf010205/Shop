package com.shuaibi.shop.shop.service.impl;

import com.shuaibi.shop.common.entity.table.PmsProduct;
import com.shuaibi.shop.common.mapper.PmsProductMapper;
import com.shuaibi.shop.shop.service.IPmsShopProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsShopProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsShopProductService {

}
