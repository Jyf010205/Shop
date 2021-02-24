package com.shuaibi.shop.shop.entity.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author: jianyufeng
 * @date: 2021/2/18 15:05
 * @description:
 */
public class UpdateProductSkuRequest {
    private Long id;

    @ApiModelProperty(value = "售价")
    private Double skuPrice;

    @ApiModelProperty(value = "库存")
    private Integer skuStock;

    @ApiModelProperty(value = "库存预警值")
    private Integer skuLowStock;

    @ApiModelProperty(value = "不同规格商品图片")
    private String skuPics;
}
