package com.shuaibi.shop.shop.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: jianyufeng
 * @date: 2021/2/9 10:34
 * @description:
 */
@Data
public class CreateProductSkuRequest {
    @ApiModelProperty(value = "售价")
    private Double skuPrice;

    @ApiModelProperty(value = "库存")
    private Integer skuStock;

    @ApiModelProperty(value = "库存预警值")
    private Integer skuLowStock;

    @ApiModelProperty(value = "不同规格商品图片")
    private String skuPics;

    @ApiModelProperty(value = "规格属性列表")
    private List<SkuAttributeValue> skuAttributeValueList;

}
