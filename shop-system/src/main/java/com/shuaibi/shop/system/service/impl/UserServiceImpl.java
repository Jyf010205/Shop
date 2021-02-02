package com.shuaibi.shop.system.service.impl;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaibi.shop.common.entity.BatchRequest;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.common.mapper.UserMapper;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.system.entity.request.FindUserListRequest;
import com.shuaibi.shop.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author: jianyufeng
 * @date: 2021/1/19 14:29
 * @description:
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 查询用户列表
     * @param request
     * @return
     */
    @Override
    public IPage<User> findUserList(FindUserListRequest request) {
        IPage<User> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.getBaseMapper().selectPage(page, new LambdaQueryWrapper<User>()
                .like(Optional.ofNullable(request.getUsername()).isPresent(), User::getUsername, request.getUsername()));
    }

    /**
     * 修改用户状态
     * @param id
     * @param ableStatus
     * @return
     */
    @Override
    public boolean ableUser(Long id, Boolean ableStatus) {
        return update(new LambdaUpdateWrapper<User>().set(User::getStatus,ableStatus).eq(User::getId,id));
    }

    /**
     * 批量操作
     * @param request
     * @return
     */
    @Override
    public boolean batch(BatchRequest request) {
        HttpMethod method = request.getMethod();
        JSONArray requestBody = request.getBody();
        switch (method){
            case POST:
                List<User> saveUserList = requestBody.toList(User.class);
                return saveBatch(saveUserList);
            case PUT:
                List<User> updateUserList = requestBody.toList(User.class);
                return updateBatchById(updateUserList);
            case DELETE:
                List<Long> userIds = requestBody.toList(Long.class);
                return removeByIds(userIds);
            default:
                Asserts.fail("参数错误");
        }
        return false;
    }
}
