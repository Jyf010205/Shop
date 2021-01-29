package com.shuaibi.shop.shop.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.shuaibi.shop.common.entity.table.PmsShop;
import com.shuaibi.shop.common.mapper.PmsShopMapper;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.common.utils.SnowflakeIdWorker;
import com.shuaibi.shop.shop.entity.request.CreateShopRequest;
import com.shuaibi.shop.shop.entity.request.UpdateShopRequest;
import com.shuaibi.shop.shop.service.IPmsShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 店铺表 服务实现类
 * </p>
 *
 * @author jianyufeng
 * @since 2021-01-29
 */
@Service
public class PmsShopServiceImpl extends ServiceImpl<PmsShopMapper, PmsShop> implements IPmsShopService {
    @Autowired
    @Qualifier("shopSnowflakeIdWorker")
    private SnowflakeIdWorker shopSnowflakeIdWorker;

    /**
     * 创建店铺
     * @param request
     * @param userId
     * @return
     */
    @Override
    public Optional<PmsShop> createShop(CreateShopRequest request, String userId) {
        Optional<PmsShop> existShop = Optional.ofNullable(getOne(new LambdaQueryWrapper<PmsShop>().eq(PmsShop::getShopName, request.getShopName())));
        if (existShop.isPresent()){
            Asserts.fail("店铺名已存在");
        }
        PmsShop pmsShop = new PmsShop();
        BeanUtils.copyProperties(request,pmsShop);
        pmsShop.setShopUserId(Long.parseLong(userId));
        pmsShop.setShopId(shopSnowflakeIdWorker.nextId());
        pmsShop.setOpenStatus(true);
        save(pmsShop);
        return Optional.of(pmsShop);
    }

    /**
     * 修改店铺
     * @param request
     * @param userId
     * @return
     */
    @Override
    public Optional<PmsShop> updateShop(UpdateShopRequest request, String userId) {
        if (!StrUtil.equals(request.getShopUserId().toString(),userId)){
            Asserts.fail("只能修改本店铺的信息");
        }
        update(new LambdaUpdateWrapper<PmsShop>()
                .set(PmsShop::getShopName, request.getShopName())
                .set(PmsShop::getShopDescription, request.getShopDescription())
                .eq(PmsShop::getShopId, request.getShopId()));
        return Optional.ofNullable(getOne(new LambdaQueryWrapper<PmsShop>().eq(PmsShop::getShopId,request.getShopId())));
    }
}
