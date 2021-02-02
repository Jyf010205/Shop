package com.shuaibi.shop.shop.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: jianyufeng
 * @date: 2021/2/1 15:09
 * @description:
 */
@Data
public class CreateProductRequest {
    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品描述")
    private String productDescriptoin;

    @ApiModelProperty(value = "商品类目")
    private Long productCategoryId;

    @ApiModelProperty(value = "商品类目名称")
    private String productCategoryName;

    @ApiModelProperty(value = "产品图片限制为5张，以逗号分割")
    private String productPics;

    @ApiModelProperty(value = "网页详情内容")
    private String productDetailHtml;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "新品状态：0->不是新品；1->新品")
    private Boolean newStatus;

    @ApiModelProperty(value = "售价")
    private BigDecimal price;

    @ApiModelProperty(value = "库存")
    private Long stock;

    @ApiModelProperty(value = "库存预警值")
    private Long lowStock;

    @ApiModelProperty(value = "运费模板ID")
    private Long freightTemplateId;
}
