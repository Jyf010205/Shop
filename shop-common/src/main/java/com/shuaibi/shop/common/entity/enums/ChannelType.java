package com.shuaibi.shop.common.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

/**
 * @author: jianyufeng
 * @date: 2021/1/20 15:21
 * @description:
 */
@Getter
public enum ChannelType implements IEnum<Integer> {
    PC(1,"PC"),
    ANDROID(2,"安卓"),
    IOS(3,"IOS"),
    MINI_PROGRAM(4,"小程序"),
    No_Channel(5,"未知");

    ChannelType(Integer code, String descp) {
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
