package com.shuaibi.shop.shop.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: jianyufeng
 * @date: 2021/1/29 14:46
 * @description:
 */
@Data
public class CreateShopRequest {
    @ApiModelProperty(value = "店铺名称")
    @NotBlank(message = "店铺名称不能为空")
    private String shopName;

    @ApiModelProperty(value = "店铺描述")
    @NotBlank(message = "店铺描述不能为空")
    private String shopDescription;
}
