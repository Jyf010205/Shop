package com.shuaibi.shop.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaibi.shop.common.entity.BatchRequest;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.system.entity.request.FindUserListRequest;
import com.shuaibi.shop.system.entity.request.InsertUserRequest;

import java.util.Optional;

/**
 * @author: jianyufeng
 * @date: 2021/1/19 14:30
 * @description:
 */
public interface IUserService extends IService<User> {
    IPage<User> findUserList(FindUserListRequest request);

    Optional<User> createUser(InsertUserRequest request);

    boolean ableUser(Long id, Boolean ableStatus);

    boolean batch(BatchRequest request);
}
