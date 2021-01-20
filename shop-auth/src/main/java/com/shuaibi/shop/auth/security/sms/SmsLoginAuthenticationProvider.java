package com.shuaibi.shop.auth.security.sms;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.shuaibi.shop.auth.entity.SmsCode;
import com.shuaibi.shop.auth.security.SystemUserDetailsServiceImpl;
import com.shuaibi.shop.common.constant.RedisKey;
import com.shuaibi.shop.common.exception.SmsLoginExpection;
import com.shuaibi.shop.common.utils.SpringContextHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author: jianyufeng
 * @date: 2021/1/18 14:53
 * @description:
 */
public class SmsLoginAuthenticationProvider implements AuthenticationProvider {
    private final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        StringRedisTemplate redisTemplate = SpringContextHolder.getBean(StringRedisTemplate.class);

        Long mobile = (Long) authentication.getPrincipal();
        String code = (String) authentication.getCredentials();
        String smsCodeStr = (String) redisTemplate.opsForHash().get(RedisKey.SMS_CODE_KEY, mobile.toString());
        SmsCode smsCode = JSONUtil.toBean(smsCodeStr, SmsCode.class);
        try {
            //校验短信验证码
            if (code == null){
                throw new SmsLoginExpection("验证码不能为空！");
            }else if (smsCode == null){
                throw new SmsLoginExpection("验证码不存在，请重新发送！");
            }else if (DateUtil.compare(new DateTime(),smsCode.getExpiredTime()) > 0){
                throw new SmsLoginExpection("验证码已过期，请重新发送！");
            }else if (!StrUtil.equals(code,smsCode.getCode())){
                throw new SmsLoginExpection("验证码不正确！");
            }
        }finally {
            //验证完毕，删除验证码
            redisTemplate.opsForHash().delete(RedisKey.SMS_CODE_KEY,mobile.toString());
        }

        SystemUserDetailsServiceImpl systemUserDetailsService = (SystemUserDetailsServiceImpl) userDetailsService;

        UserDetails user = systemUserDetailsService.loadUserByModile(mobile);
        //重新生成新的Authentication(放入权限)
        return createSuccessAuthentication(user, authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsLoginAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    private Authentication createSuccessAuthentication(UserDetails user, Authentication authentication) {
        //将Userdetail放入principal中
        SmsLoginAuthenticationToken result = new SmsLoginAuthenticationToken(
                user, authentication.getCredentials(),
                authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());

        return result;
    }
}
