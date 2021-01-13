package com.shuaibi.shop.auth.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shuaibi.shop.auth.mapper.UserMapper;
import com.shuaibi.shop.auth.mapper.UserRoleRelationMapper;
import com.shuaibi.shop.auth.service.SystemUserService;
import com.shuaibi.shop.common.entity.table.Permission;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.common.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: jianyufeng
 * @date: 2021/1/13 16:01
 * @description: SystemUserService实现类
 */
@Slf4j
@Service
public class SystemUserServiceImpl implements SystemUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

    @Override
    public User getAdminByUsername(String username) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    /**
     * 用户注册
     * @param userParam
     * @return
     */
    @Override
    public Optional<User> register(User userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setCreateTime(new DateTime());
        user.setStatus(User.STATUS_VALID);
        //查询是否有相同用户名的用户
        List<User> userList = userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()));
        if (userList.size() > 0) {
            return Optional.empty();
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userMapper.insert(user);
        return Optional.of(user);
    }

    /**
     * 登录以后返回token
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public Optional<String> login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return Optional.ofNullable(token);
    }


    /**
     * 获取用户权限
     * @param userId
     * @return
     */
    @Override
    public List<Permission> getPermissionList(Long userId) {
        return userRoleRelationMapper.getPermissionList(userId).stream().filter(permission -> Optional.ofNullable(permission.getValue()).isPresent()).collect(Collectors.toList());
    }

    /**
     * 异步记录登录时间
     * @param username
     */
    @Async
    @Override
    public void updateLoginTime(String username){
        userMapper.update(null,new LambdaUpdateWrapper<User>()
                .set(User::getLoginTime,new DateTime())
                .eq(User::getUsername,username));
    }
}
