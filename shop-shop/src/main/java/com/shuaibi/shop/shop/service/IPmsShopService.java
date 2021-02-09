package com.shuaibi.shop.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaibi.shop.common.entity.table.PmsShop;
import com.shuaibi.shop.shop.entity.request.CreateShopRequest;
import com.shuaibi.shop.shop.entity.request.UpdateShopRequest;

import java.util.List;

/**
 * <p>
 * 店铺表 服务类
 * </p>
 *
 * @author jianyufeng
 * @since 2021-01-29
 */
public interface IPmsShopService extends IService<PmsShop> {

    List<PmsShop> pmsShopService(String userId);

    Boolean createShop(CreateShopRequest request, String userId);

    Boolean updateShop(UpdateShopRequest request, String userId);
}
