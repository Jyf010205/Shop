package com.shuaibi.shop.shop.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: jianyufeng
 * @date: 2021/1/29 15:09
 * @description:
 */
@Data
public class UpdateShopRequest {
    @ApiModelProperty(value = "店铺ID")
    @NotNull(message = "店铺ID不能为空")
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    @NotBlank(message = "店铺名称不能为空")
    private String shopName;

    @ApiModelProperty(value = "店铺描述")
    @NotBlank(message = "店铺描述不能为空")
    private String shopDescription;

    @ApiModelProperty(value = "店主用户ID")
    @NotNull(message = "店主用户ID不能为空")
    private Long shopUserId;
}
