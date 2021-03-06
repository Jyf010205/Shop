package com.shuaibi.shop.system.entity.request;

import com.shuaibi.shop.common.annotation.Mobile;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: jianyufeng
 * @date: 2021/1/21 11:58
 * @description:
 */
@Data
public class RegisterRequest {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号不能为空")
    @Mobile(message = "手机号格式不正确")
    private Long mobile;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String code;

}
