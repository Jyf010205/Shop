package com.shuaibi.shop.shop.service;

import com.shuaibi.shop.common.entity.table.PmsShop;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaibi.shop.shop.entity.request.CreateShopRequest;
import com.shuaibi.shop.shop.entity.request.UpdateShopRequest;

import java.util.Optional;

/**
 * <p>
 * 店铺表 服务类
 * </p>
 *
 * @author jianyufeng
 * @since 2021-01-29
 */
public interface IPmsShopService extends IService<PmsShop> {

    Optional<PmsShop> createShop(CreateShopRequest request, String userId);

    Optional<PmsShop> updateShop(UpdateShopRequest request, String userId);
}
