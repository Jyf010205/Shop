package com.shuaibi.shop.common.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 不包邮运费规则表
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PmsFreightTemplateCharge对象", description="不包邮运费规则表")
public class PmsFreightTemplateCharge implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "模板ID")
    @TableField("FREIGHT_TEMPLATE_ID")
    private Long freightTemplateId;

    @ApiModelProperty(value = "收费城市IDS")
    @TableField("CHARGE_CITY_IDS")
    private String chargeCityIds;

    @ApiModelProperty(value = "首件数量")
    @TableField("FIRST_COUNT")
    private Integer firstCount;

    @ApiModelProperty(value = "首件价格")
    @TableField("FIRST_PRICE")
    private Double firstPrice;

    @ApiModelProperty(value = "续件数量")
    @TableField("CONTINUE_COUNT")
    private Integer continueCount;

    @ApiModelProperty(value = "续件价格")
    @TableField("CONTINUE_PRICE")
    private Double continuePrice;

}
