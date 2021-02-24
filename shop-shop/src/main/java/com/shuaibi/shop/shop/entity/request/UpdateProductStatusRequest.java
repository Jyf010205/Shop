package com.shuaibi.shop.shop.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: jianyufeng
 * @date: 2021/2/23 14:20
 * @description:
 */
@Data
public class UpdateProductStatusRequest {
    private Long id;

    @ApiModelProperty(value = "上架状态：0->下架；1->上架")
    private Boolean publishStatus;

    @ApiModelProperty(value = "新品状态：0->不是新品；1->新品")
    private Boolean newStatus;
}
