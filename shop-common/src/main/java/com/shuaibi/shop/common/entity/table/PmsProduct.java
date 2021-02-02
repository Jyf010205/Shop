package com.shuaibi.shop.common.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 商品信息表
 * </p>
 *
 * @author jianyhufeng
 * @since 2021-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PmsProduct对象", description="商品信息表")
public class PmsProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号（自增主键）")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品编号（规则：年份+商铺编号+5位流水号）")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField("PRODUCT_ID")
    private Long productId;

    @ApiModelProperty(value = "商品名称")
    @TableField("PRODUCT_NAME")
    private String productName;

    @ApiModelProperty(value = "商品描述")
    @TableField("PRODUCT_DESCRIPTOIN")
    private String productDescriptoin;

    @ApiModelProperty(value = "商品类目")
    @TableField("PRODUCT_CATEGORY_ID")
    private Long productCategoryId;

    @ApiModelProperty(value = "商品类目名称")
    @TableField("PRODUCT_CATEGORY_NAME")
    private String productCategoryName;

    @ApiModelProperty(value = "产品图片限制为5张，以逗号分割")
    @TableField("PRODUCT_PICS")
    private String productPics;

    @ApiModelProperty(value = "网页详情内容")
    @TableField("PRODUCT_DETAIL_HTML")
    private String productDetailHtml;

    @ApiModelProperty(value = "店铺ID")
    @TableField("SHOP_ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    @TableField("SHOP_NAME")
    private String shopName;

    @ApiModelProperty(value = "删除状态：0->未删除；1->已删除")
    @TableField("DELETE_STATUS")
    private Boolean deleteStatus;

    @ApiModelProperty(value = "上架状态：0->下架；1->上架")
    @TableField("PUBLISH_STATUS")
    private Boolean publishStatus;

    @ApiModelProperty(value = "新品状态：0->不是新品；1->新品")
    @TableField("NEW_STATUS")
    private Boolean newStatus;

    @ApiModelProperty(value = "审核状态：0->未审核；1->审核通过")
    @TableField("RECOMMAND_STATUS")
    private Boolean recommandStatus;

    @ApiModelProperty(value = "销量")
    @TableField("SALES")
    private Integer sales;

    @ApiModelProperty(value = "售价")
    @TableField("PRICE")
    private Double price;

    @ApiModelProperty(value = "库存")
    @TableField("STOCK")
    private Integer stock;

    @ApiModelProperty(value = "库存预警值")
    @TableField("LOW_STOCK")
    private Integer lowStock;

    @ApiModelProperty(value = "运费模板ID")
    @TableField("FREIGHT_TEMPLATE_ID")
    private Long freightTemplateId;


}
