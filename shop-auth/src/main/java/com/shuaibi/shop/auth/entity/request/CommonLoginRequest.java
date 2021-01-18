package com.shuaibi.shop.auth.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: jianyufeng
 * @date: 2021/1/18 16:23
 * @description:
 */
@Data
public class CommonLoginRequest {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;
}
