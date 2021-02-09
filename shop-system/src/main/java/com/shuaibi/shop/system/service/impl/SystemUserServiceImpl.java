package com.shuaibi.shop.system.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.shuaibi.shop.common.constant.RedisKey;
import com.shuaibi.shop.common.entity.SystemUserDetails;
import com.shuaibi.shop.common.entity.enums.ChannelType;
import com.shuaibi.shop.common.entity.table.Permission;
import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.common.entity.table.UserLoginLog;
import com.shuaibi.shop.common.mapper.UserLoginLogMapper;
import com.shuaibi.shop.common.mapper.UserMapper;
import com.shuaibi.shop.common.mapper.UserRoleRelationMapper;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.common.utils.SnowflakeIdWorker;
import com.shuaibi.shop.system.entity.SmsCode;
import com.shuaibi.shop.system.entity.request.RegisterRequest;
import com.shuaibi.shop.system.service.ISystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
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
public class SystemUserServiceImpl implements ISystemUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    @Qualifier("UserSnowflakeIdWorker")
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;
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
     * @param request
     */
    @Override
    public Optional<User> register(RegisterRequest request) {
        Long mobile = request.getMobile();
        String code = request.getCode();
        String smsCodeStr = (String) redisTemplate.opsForHash().get(RedisKey.SMS_CODE_KEY, mobile.toString());
        SmsCode smsCode = JSONUtil.toBean(smsCodeStr, SmsCode.class);
        try {
            //校验短信验证码
            if (smsCode == null){
                Asserts.fail("验证码不存在，请重新发送！");
            }else if (DateUtil.compare(new DateTime(),smsCode.getExpiredTime()) > 0){
                Asserts.fail("验证码已过期，请重新发送！");
            }else if (!StrUtil.equals(code,smsCode.getCode())){
                Asserts.fail("验证码不正确！");
            }
        }finally {
            //验证完毕，删除验证码
            redisTemplate.opsForHash().delete(RedisKey.SMS_CODE_KEY,mobile.toString());
        }

        User user = new User();
        BeanUtils.copyProperties(request, user);
        user.setUserId(snowflakeIdWorker.nextId());
        user.setRegisterTime(new DateTime());
        user.setIcon("http://121.5.40.72:9000/shop/2021-02-05/default.png");
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
     * @param authentication
     */
    @Async
    @Override
    public void updateLoginTime(Authentication authentication){
        Object details = authentication.getDetails();
        JSONObject detailJson = new JSONObject(details);
        SystemUserDetails userDetails = (SystemUserDetails) authentication.getPrincipal();

        userMapper.update(null,new LambdaUpdateWrapper<User>()
                .set(User::getLoginTime,new DateTime())
                .eq(User::getUserId,userDetails.getUserId()));

        UserLoginLog userLoginLog = UserLoginLog.builder()
                .userId(userDetails.getUserId())
                .ip(detailJson.getStr("remoteAddress"))
                .loginChannel(detailJson.getEnum(ChannelType.class,"channel"))
                .createTime(new DateTime()).build();
        userLoginLogMapper.insert(userLoginLog);
    }
}
