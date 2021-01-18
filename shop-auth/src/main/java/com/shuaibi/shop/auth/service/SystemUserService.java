package com.shuaibi.shop.auth.service;

import com.shuaibi.shop.common.entity.table.Permission;
import com.shuaibi.shop.common.entity.table.User;

import java.util.List;
import java.util.Optional;

/**
 * @author: jianyufeng
 * @date: 2021/1/13 16:01
 * @description: 后台管理员Service
 */
public interface SystemUserService {
    /**
     * 根据用户名获取用户
     */
    User getUserByUsername(String username);

    /**
     * 根据手机号获取用户
     */
    User getUserByMobile(Long mobile);

    /**
     * 注册功能
     */
    Optional<User> register(User userParam);

    /**
     * 发送短信验证码
     * @param mobile
     * @return
     */
    Long smsCode(Long mobile);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<Permission> getPermissionList(Long userId);

    /**
     * 异步记录登录时间
     * @param username
     */
    void updateLoginTime(String username);
}
