package com.shuaibi.shop.common.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 条件包邮运费规则表
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PmsFreightTemplateFree对象", description="条件包邮运费规则表")
@TableName("pms_freight_template_condition_free")
public class PmsFreightTemplateFree implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "模板ID")
    @TableField("FREIGHT_TEMPLATE_ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long freightTemplateId;

    @ApiModelProperty(value = "包邮城市IDS")
    @TableField("FREE_CITY_IDS")
    private String freeCityIds;

    @ApiModelProperty(value = "包邮件数")
    @TableField("FREE_COUNT")
    private Integer freeCount;

    @ApiModelProperty(value = "包邮金额")
    @TableField("FREE_PRICE")
    private Double freePrice;


}
