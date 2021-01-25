package com.shuaibi.shop.system.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: jianyufeng
 * @date: 2021/1/18 16:25
 * @description:
 */
@Data
public class SmsLoginRequest {
    @ApiModelProperty(value = "手机号")
    private Long mobile;

    @ApiModelProperty(value = "验证码")
    private String code;
}
