package com.shuaibi.shop.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shuaibi.shop.common.entity.table.PmsShopFans;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.shop.entity.request.GetFansListRequest;

/**
 * <p>
 * 店铺粉丝表 服务类
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-04
 */
public interface IPmsShopFansService extends IService<PmsShopFans> {

    IPage<User> getFansList(GetFansListRequest request);
}
