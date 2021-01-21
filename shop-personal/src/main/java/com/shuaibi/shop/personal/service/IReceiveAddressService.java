package com.shuaibi.shop.personal.service;

import com.shuaibi.shop.common.entity.table.ReceiveAddress;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员收货地址表 服务类
 * </p>
 *
 * @author syq
 * @since 2021-01-21
 */
public interface IReceiveAddressService extends IService<ReceiveAddress> {

    boolean insert(ReceiveAddress address, String userId);

    boolean updateDefaultStatus(Long id, String userId);
}
