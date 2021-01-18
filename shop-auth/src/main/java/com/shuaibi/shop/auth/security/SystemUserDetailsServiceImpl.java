package com.shuaibi.shop.auth.security;

import com.shuaibi.shop.auth.entity.SystemUserDetails;
import com.shuaibi.shop.auth.service.SystemUserService;
import com.shuaibi.shop.common.entity.table.Permission;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.common.exception.SmsLoginExpection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @author: jianyufeng
 * @date: 2021/1/18 15:39
 * @description:
 */
public class SystemUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SystemUserService systemUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = systemUserService.getUserByUsername(username);
        if (user != null) {
            List<Permission> permissionList = systemUserService.getPermissionList(user.getUserId());
            return new SystemUserDetails(user,permissionList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    public UserDetails loadUserByModile(Long mobile){
        User user = systemUserService.getUserByMobile(mobile);
        if (user != null) {
            List<Permission> permissionList = systemUserService.getPermissionList(user.getUserId());
            return new SystemUserDetails(user,permissionList);
        }
        throw new SmsLoginExpection("当前手机号还未注册");
    }
}
