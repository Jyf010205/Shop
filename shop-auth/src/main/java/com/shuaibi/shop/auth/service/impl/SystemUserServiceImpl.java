package com.shuaibi.shop.auth.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.shuaibi.shop.auth.entity.SmsCode;
import com.shuaibi.shop.auth.mapper.UserMapper;
import com.shuaibi.shop.auth.mapper.UserRoleRelationMapper;
import com.shuaibi.shop.auth.service.SystemUserService;
import com.shuaibi.shop.common.constant.RedisKey;
import com.shuaibi.shop.common.entity.table.Permission;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.common.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(rollbackFor = Exception.class)
public class SystemUserServiceImpl implements SystemUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public User getUserByMobile(Long mobile) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getMobile, mobile));
    }

    /**
     * 用户注册
     * @param userParam
     */
    @Override
    public Optional<User> register(User userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setUserId(snowflakeIdWorker.nextId());
        user.setCreateTime(new DateTime());
        user.setStatus(true);
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
     * 发送短信验证码
     * @param mobile
     * @return
     */
    @Override
    public Long smsCode(Long mobile) {
        String code = RandomStringUtils.randomNumeric(6);
        log.info("===============手机号:{}发送短信验证码:{}===============",mobile,code);
        /**
         * 生成验证码，60秒过期时间 放入Redis中
         * 发送短信功能暂未实现
         */
        SmsCode smsCode = SmsCode.builder()
                .mobile(mobile)
                .code(code)
                .expiredTime(DateUtil.offsetSecond(new DateTime(), 60)).build();
        redisTemplate.opsForHash().put(RedisKey.SMS_CODE_KEY,mobile.toString(),JSONUtil.toJsonStr(smsCode));
        return mobile;
    }

    /**
     * 获取用户权限
     * @param userId
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
