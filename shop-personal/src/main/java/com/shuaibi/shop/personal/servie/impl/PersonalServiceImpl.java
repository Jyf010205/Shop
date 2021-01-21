package com.shuaibi.shop.personal.servie.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.common.mapper.UserMapper;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.personal.entity.request.ModifyPasswordRequest;
import com.shuaibi.shop.personal.entity.request.UpdateUserRequest;
import com.shuaibi.shop.personal.servie.IPersonalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: jianyufeng
 * @date: 2021/1/20 22:48
 * @description:
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PersonalServiceImpl implements IPersonalService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 获取个人信息
     * @param userId
     * @return
     */
    @Override
    public User getPersonalInfo(String userId) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserId, userId));
        user.setPassword(null);
        return user;
    }

    /**
     * 修改个人信息
     * @param request
     * @param userId
     * @return
     */
    @Override
    public Integer update(UpdateUserRequest request, String userId) {
        if (!StrUtil.equals(request.getUserId().toString(),userId)){
            Asserts.fail("只能修改自己的信息");
        }
        User user = new User();
        BeanUtils.copyProperties(request,user);
        return userMapper.update(user,new LambdaQueryWrapper<User>().eq(User::getUserId,userId));
    }

    /**
     * 修改密码
     * @param request
     * @param userId
     * @return
     */
    @Override
    public Integer modifyPassword(ModifyPasswordRequest request, String userId) {
        String oldPassword = request.getOldPassword();
        String newPassword = request.getNewPassword();

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserId, userId));
        //匹配旧密码
        if (!passwordEncoder.matches(oldPassword, user.getPassword())){
            Asserts.fail("旧密码输入错误");
        }
        String encodePassword = passwordEncoder.encode(newPassword);
        return userMapper.update(null,new LambdaUpdateWrapper<User>().set(User::getPassword,encodePassword).eq(User::getUserId,userId));
    }
}
