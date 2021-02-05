package com.shuaibi.shop.system.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: jianyufeng
 * @date: 2021/2/5 10:43
 * @description:
 */
@Data
public class InsertUserRequest {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

}
