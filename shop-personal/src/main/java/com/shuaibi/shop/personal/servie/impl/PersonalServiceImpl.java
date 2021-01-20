package com.shuaibi.shop.personal.servie.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.common.mapper.UserMapper;
import com.shuaibi.shop.personal.servie.IPersonalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    /**
     * 获取个人信息
     * @param userId
     * @return
     */
    @Override
    public User getPersonalInfo(String userId) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserId,userId));
    }
}
