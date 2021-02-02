package com.shuaibi.shop.common.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.shuaibi.shop.common.entity.enums.FreightTemplateType;
import com.shuaibi.shop.common.entity.enums.TransportType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 运费模版表
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PmsFreightTemplate对象", description="运费模版表")
public class PmsFreightTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "模板ID")
    @TableField("FREIGHT_TEMPLATE_ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long freightTemplateId;

    @ApiModelProperty(value = "店铺ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField("SHOP_ID")
    private Long shopId;

    @ApiModelProperty(value = "模板名称")
    @TableField("TEMPLATE_NAME")
    private String templateName;

    @ApiModelProperty(value = "1->包邮 2->按条件包邮 3->不包邮")
    @TableField("TEMPLATE_TYPE")
    private FreightTemplateType templateType;

    @ApiModelProperty(value = "运送方式,1->EMS 2->顺丰 3->圆通 4->申通")
    @TableField("TRANSPORT_TYPE")
    private TransportType transportType;

    @TableField(exist = false)
    @ApiModelProperty(value = "条件包邮收费规则")
    private List<PmsFreightTemplateFree> freightTemplateFreeList;

    @TableField(exist = false)
    @ApiModelProperty(value = "不包邮收费规则")
    private List<PmsFreightTemplateCharge> freightTemplateChargeList;

}
