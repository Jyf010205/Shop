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
 * 店铺表
 * </p>
 *
 * @author jianyhufeng
 * @since 2021-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PmsShop对象", description="店铺表")
public class PmsShop implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "店铺ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField("SHOP_ID")
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    @TableField("SHOP_NAME")
    private String shopName;

    @ApiModelProperty(value = "店铺描述")
    @TableField("SHOP_DESCRIPTION")
    private String shopDescription;

    @ApiModelProperty(value = "店主用户ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField("SHOP_USER_ID")
    private Long shopUserId;

    @ApiModelProperty(value = "店铺分数")
    @TableField("SHOP_SCORE")
    private Long shopScore;

    @ApiModelProperty(value = "店铺星级")
    @TableField("SHOP_STAR")
    private Integer shopStar;

    @ApiModelProperty(value = "店铺销量")
    @TableField("SHOP_SALES")
    private Long shopSales;

    @ApiModelProperty(value = "开启状态：0->关闭；1->开启")
    @TableField("OPEN_STATUS")
    private Boolean openStatus;


}
