package com.shuaibi.shop.common.entity.table;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author syq
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PmsProductSku对象", description="")
public class PmsProductSku implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;

    @ApiModelProperty(value = "商品编号（规则：年份+商铺编号+5位流水号）")
    @TableField("PRODUCT_ID")
    private Long productId;

    @ApiModelProperty(value = "不同规格商品ID")
    @TableField("SKU_ID")
    private Long skuId;

    @ApiModelProperty(value = "销量")
    @TableField("SKU_SALES")
    private Long skuSales;

    @ApiModelProperty(value = "售价")
    @TableField("SKU_PRICE")
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "库存")
    @TableField("SKU_STOCK")
    private Long skuStock;

    @ApiModelProperty(value = "库存预警值")
    @TableField("SKU_LOW_STOCK")
    private Long skuLowStock;

    @ApiModelProperty(value = "不同规格商品图片")
    @TableField("SKU_PICS")
    private String skuPics;

    @ApiModelProperty(value = "销售属性,JSON格式 [ { key: 颜色, value: 黑色 }, { key: 容量, value: 32G } ] ")
    @TableField("SKU_DATA")
    private String skuData;


}
