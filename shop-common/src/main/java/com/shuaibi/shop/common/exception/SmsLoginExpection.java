package com.shuaibi.shop.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author: jianyufeng
 * @date: 2021/1/18 15:01
 * @description: 自定义短信登录异常
 */
public class SmsLoginExpection extends AuthenticationException {
    public SmsLoginExpection(String msg) {
        super(msg);
    }

    public SmsLoginExpection(String msg, Throwable t) {
        super(msg, t);
    }
}
