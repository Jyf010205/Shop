package com.shuaibi.shop.common.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private Long freightTemplateId;

    @ApiModelProperty(value = "包邮城市IDS")
    @TableField("FREE_CITY_IDS")
    @NotBlank(message = "包邮城市不能为空")
    private String freeCityIds;

    @ApiModelProperty(value = "包邮件数")
    @TableField("FREE_COUNT")
    @NotNull(message = "包邮件数不能为空")
    private Integer freeCount;

    @ApiModelProperty(value = "包邮金额")
    @TableField("FREE_PRICE")
    @NotNull(message = "包邮金额不能为空")
    private Double freePrice;


}
