package com.shuaibi.shop.shop.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: jianyufeng
 * @date: 2021/2/9 11:43
 * @description:
 */
@Data
public class SkuAttributeValue {
    @ApiModelProperty(value = "产品属性名称")
    private String attributeName;

    @ApiModelProperty(value = "产品参数")
    private String attributeValue;
}
