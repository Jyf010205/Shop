package com.shuaibi.shop.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaibi.shop.auth.entity.request.FindUserListRequest;
import com.shuaibi.shop.common.entity.BatchRequest;
import com.shuaibi.shop.common.entity.table.User;

/**
 * @author: jianyufeng
 * @date: 2021/1/19 14:30
 * @description:
 */
public interface IUserService extends IService<User> {
    IPage<User> findUserList(FindUserListRequest request);

    boolean ableUser(Long id, Boolean ableStatus);

    boolean batch(BatchRequest request);
}
