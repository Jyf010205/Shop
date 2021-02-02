package com.shuaibi.shop.common.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author: jianyufeng
 * @date: 2021/2/1 18:33
 * @description: 运送方式
 */
public enum TransportType implements IEnum<Integer> {
    EMS(1,"EMS"),
    SF(2,"顺丰"),
    YT(3,"圆通"),
    ST(4,"申通");

    TransportType(Integer code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    @EnumValue//标记数据库存的值是code
    private final int code;

    private final String descp;

    @Override
    public Integer getValue() {
        return this.code;
    }
}

