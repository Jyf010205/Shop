package com.shuaibi.shop.auth.service;

import com.shuaibi.shop.common.entity.table.Permission;
import com.shuaibi.shop.common.entity.table.User;

import java.util.List;

/**
 * @author: jianyufeng
 * @date: 2021/1/13 16:01
 * @description: 后台管理员Service
 */
public interface SystemUserService {
    /**
     * 根据用户名获取后台管理员
     */
    User getAdminByUsername(String username);

    /**
     * 注册功能
     */
    User register(User userParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<Permission> getPermissionList(Long userId);
}
