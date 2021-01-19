package com.shuaibi.shop.common.utils;

import com.shuaibi.shop.common.entity.enums.IErrorCode;
import com.shuaibi.shop.common.exception.ApiException;

/**
 * @author: jianyufeng
 * @date: 2021/1/13 21:51
 * @description: 断言处理类，用于抛出各种API异常
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}

