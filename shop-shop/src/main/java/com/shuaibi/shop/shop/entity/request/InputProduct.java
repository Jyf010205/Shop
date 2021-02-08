package com.shuaibi.shop.shop.entity.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.shuaibi.shop.common.entity.table.PmsProductAttribute;
import com.shuaibi.shop.common.entity.table.PmsProductAttributeValue;
import com.shuaibi.shop.common.entity.table.PmsProductSku;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class InputProduct {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号（自增主键）")
    private Long id;

    @ApiModelProperty(value = "商品编号（规则：年份+商铺编号+5位流水号）")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long productId;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "删除状态：0->未删除；1->已删除")
    private Boolean deleteStatus;

    @ApiModelProperty(value = "上架状态：0->下架；1->上架")
    private Boolean publishStatus;

    @ApiModelProperty(value = "新品状态：0->不是新品；1->新品")
    private Boolean newStatus;

    @ApiModelProperty(value = "审核状态：0->未审核；1->审核通过")
    private Boolean recommandStatus;

    @ApiModelProperty(value = "销量")
    private Integer sales;

    @ApiModelProperty(value = "售价")
    private Double price;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "库存预警值")
    private Integer lowStock;

    @ApiModelProperty(value = "运费模板ID")
    private Long freightTemplateId;

    @ApiModelProperty(value = "属性列表")
    private List<PmsProductAttributeValue> pmsProductAttributeValueList;

    @ApiModelProperty(value = "各属性值")
    private List<PmsProductSku> pmsProductSkuList;

}
