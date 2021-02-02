package com.shuaibi.shop.common.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

/**
 * @author: jianyufeng
 * @date: 2021/2/1 15:42
 * @description: 运费模板枚举值
 */
@Getter
public enum FreightTemplateType implements IEnum<Integer> {
    FREE(1,"包邮"),
    CONDITION_FREE(2,"按条件包邮"),
    NOT_FREE(3,"不包邮");

    FreightTemplateType(Integer code, String descp) {
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
