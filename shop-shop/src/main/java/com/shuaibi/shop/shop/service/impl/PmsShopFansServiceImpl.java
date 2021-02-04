package com.shuaibi.shop.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaibi.shop.common.entity.table.PmsShopFans;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.common.mapper.PmsShopFansMapper;
import com.shuaibi.shop.common.mapper.UserMapper;
import com.shuaibi.shop.shop.entity.request.GetFansListRequest;
import com.shuaibi.shop.shop.service.IPmsShopFansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 店铺粉丝表 服务实现类
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-04
 */
@Service
public class PmsShopFansServiceImpl extends ServiceImpl<PmsShopFansMapper, PmsShopFans> implements IPmsShopFansService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取粉丝列表
     * @param request
     * @return
     */
    @Override
    public IPage<User> getFansList(GetFansListRequest request) {
        IPage<PmsShopFans> page = new Page<>(request.getPageNum(), request.getPageSize());
        IPage<PmsShopFans> ShopFansPage = this.page(page, new LambdaQueryWrapper<PmsShopFans>().eq(PmsShopFans::getShopId, request.getShopId()));
        //查询UserIds
        List<Long> UserIds = ShopFansPage.getRecords().stream().map(PmsShopFans::getUserId).collect(Collectors.toList());
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>().in(User::getUserId, UserIds));
        //重新创建分页对象
        Page<User> userPage = new Page<>(ShopFansPage.getCurrent(),ShopFansPage.getSize(),ShopFansPage.getTotal());
        return userPage.setRecords(users);
    }
}
