package com.shuaibi.shop.goods.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2021-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GGoods对象", description="")
public class GGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号（自增主键）")
    @TableId("ID")
    private Integer id;

    @ApiModelProperty(value = "商品编号（规则：年份+商铺编号+5位流水号）")
    @TableField("GOODS_ID")
    private String goodsId;

    @ApiModelProperty(value = "商品名称")
    @TableField("GOODS_NAME")
    private String goodsName;

    @ApiModelProperty(value = "商品类型")
    @TableField("GOODS_TYPE")
    private String goodsType;

    @ApiModelProperty(value = "商品单价")
    @TableField("GOODS_UNIT_PRICE")
    private BigDecimal goodsUnitPrice;

    @ApiModelProperty(value = "商品数量")
    @TableField("GOODS_NUM")
    private Integer goodsNum;

    @ApiModelProperty(value = "所属商铺编号")
    @TableField("SHOP_ID")
    private String shopId;

    @ApiModelProperty(value = "所属商铺名称")
    @TableField("SHOP_NAME")
    private String shopName;

    @ApiModelProperty(value = "生产日期")
    @TableField("PRODUCE_DATE")
    private LocalDate produceDate;

    @ApiModelProperty(value = "生产厂家")
    @TableField("PRODUCE_NAME")
    private String produceName;

    @ApiModelProperty(value = "保质期")
    @TableField("QUALITY_TIME")
    private String qualityTime;

    @ApiModelProperty(value = "创建人ID")
    @TableField("CREATER_ID")
    private Integer createrId;

    @ApiModelProperty(value = "创建人")
    @TableField("CREATER_NAME")
    private String createrName;

    @ApiModelProperty(value = "入库（创建）时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人ID")
    @TableField("UPDATER_ID")
    private Integer updaterId;

    @ApiModelProperty(value = "更新人")
    @TableField("UPDATER_NAME")
    private String updaterName;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备用字段1")
    @TableField("FILED1")
    private String filed1;

    @ApiModelProperty(value = "备用字段2")
    @TableField("FILED2")
    private String filed2;

    @ApiModelProperty(value = "备用字段2")
    @TableField("FILED3")
    private String filed3;


}
