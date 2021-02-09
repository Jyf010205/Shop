package com.shuaibi.shop.shop.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: shuaibishop
 * @Package: com.shuaibi.shop.application.entity.request
 * @Description: note
 * @Author: SYQ
 * @CreateDate: 2021/2/8 15:42
 * @UpdateUser: SYQ
 * @UpdateDate: 2021/2/8 15:42
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2021 Hundsun Technologies Inc. All Rights Reserved
 **/
@Data
public class CreateProductRequest {

    @ApiModelProperty(value = "商品名称")
    @NotBlank(message = "商品名称不能为空")
    private String productName;

    @ApiModelProperty(value = "商品描述")
    @NotBlank(message = "商品描述不能为空")
    private String productDescription;

    @ApiModelProperty(value = "商品类目")
    private Long productCategoryId;

    @ApiModelProperty(value = "商品类目名称")
    private String productCategoryName;

    @ApiModelProperty(value = "产品图片限制为5张，以逗号分割")
    @NotBlank(message = "产品图片不能为空")
    private String productPics;

    @ApiModelProperty(value = "网页详情内容")
    @NotBlank(message = "网页内容不能为空")
    private String productDetailHtml;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "上架状态：0->下架；1->上架")
    private Boolean publishStatus;

    @ApiModelProperty(value = "新品状态：0->不是新品；1->新品")
    private Boolean newStatus;

    @ApiModelProperty(value = "售价")
    @NotNull(message = "售价不能为空")
    private Double price;

    @ApiModelProperty(value = "库存")
    @NotNull(message = "库存不能为空")
    private Integer stock;

    @ApiModelProperty(value = "库存预警值")
    @NotNull(message = "库存预警值不能为空")
    private Integer lowStock;

    @ApiModelProperty(value = "运费模板ID")
    private Long freightTemplateId;

    @ApiModelProperty(value = "查询索引关键字")
    private String queryIndex;

    @ApiModelProperty(value = "属性列表")
    private List<CreateProductAttributeValueRequest> createProductAttributeValueRequestList;

    @ApiModelProperty(value = "Sku商品列表")
    private List<CreateProductSkuRequest> createProductSkuRequestList;

}
