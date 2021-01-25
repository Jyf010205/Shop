package com.shuaibi.shop.system.entity;

import cn.hutool.core.date.DateTime;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: jianyufeng
 * @date: 2021/1/18 11:41
 * @description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsCode {
    @ApiModelProperty(value = "手机号")
    private Long mobile;

    @ApiModelProperty(value = "短信验证码")
    private String code;

    @ApiModelProperty(value = "过期时间")
    private DateTime expiredTime;
}
