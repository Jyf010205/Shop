package com.shuaibi.shop.shop.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: jianyufeng
 * @date: 2021/2/9 10:09
 * @description:
 */
@Data
public class CreateProductAttributeValueRequest {
    @ApiModelProperty(value = "产品属性ID")
    private Long attributeId;

    @ApiModelProperty(value = "产品参数（参数单值，规格有多个逗号分开）")
    private String attributeValue;
}
