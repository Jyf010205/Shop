package com.shuaibi.shop.shop.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: jianyufeng
 * @date: 2021/1/29 15:09
 * @description:
 */
@Data
public class UpdateShopRequest {
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "店铺描述")
    private String shopDescription;

    @ApiModelProperty(value = "店主用户ID")
    private Long shopUserId;
}
