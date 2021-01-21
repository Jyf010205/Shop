package com.shuaibi.shop.personal.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaibi.shop.common.entity.table.ReceiveAddress;
import com.shuaibi.shop.common.mapper.ReceiveAddressMapper;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.personal.service.IReceiveAddressService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员收货地址表 服务实现类
 * </p>
 *
 * @author syq
 * @since 2021-01-21
 */
@Service
public class ReceiveAddressServiceImpl extends ServiceImpl<ReceiveAddressMapper, ReceiveAddress> implements IReceiveAddressService {

    /**
     * 新增收货地址
     * @param address
     * @param userId
     * @return
     */
    @Override
    public boolean insert(ReceiveAddress address, String userId) {
        address.setUserId(Long.valueOf(userId));
        //添加的第一个收货地址为默认收货地址
        address.setDefaultStatus((list(new LambdaQueryWrapper<ReceiveAddress>().eq(ReceiveAddress::getUserId,userId)).size() == 0));
        return save(address);
    }

    /**
     * 修改默认地址
     * @param id
     * @param userId
     * @return
     */
    @Override
    public boolean updateDefaultStatus(Long id, String userId) {
        if (!StrUtil.equals(getById(id).getUserId().toString(),userId)){
            Asserts.fail("只能修改自己的收货地址");
        }
        update(new LambdaUpdateWrapper<ReceiveAddress>().set(ReceiveAddress::getDefaultStatus,false).eq(ReceiveAddress::getUserId,userId));
        return update(new LambdaUpdateWrapper<ReceiveAddress>().set(ReceiveAddress::getDefaultStatus,true).eq(ReceiveAddress::getId,id));
    }
}
