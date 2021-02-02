package com.shuaibi.shop.shop.entity.request;

import com.shuaibi.shop.common.entity.enums.FreightTemplateType;
import com.shuaibi.shop.common.entity.enums.TransportType;
import com.shuaibi.shop.common.entity.table.PmsFreightTemplateCharge;
import com.shuaibi.shop.common.entity.table.PmsFreightTemplateFree;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: jianyufeng
 * @date: 2021/2/1 17:42
 * @description: 创建店铺运费模板请求头
 */
@Data
public class CreateFreightTemplateRequest {
    @ApiModelProperty(value = "店铺ID")
    @NotNull(message = "店铺ID不能为空")
    private Long shopId;

    @ApiModelProperty(value = "模板名称")
    @NotBlank(message = "模板名称不能为空")
    private String templateName;

    @ApiModelProperty(value = "1->包邮 2->按条件包邮 3->不包邮")
    @NotNull (message = "是否包邮不能为空")
    private FreightTemplateType templateType;

    @ApiModelProperty(value = "运送方式")
    @NotNull (message = "运送方式不能为空")
    private TransportType transportType;

    @ApiModelProperty(value = "条件包邮收费规则")
    private List<PmsFreightTemplateFree> freightTemplateFreeList;

    @ApiModelProperty(value = "不包邮收费规则")
    private List<PmsFreightTemplateCharge> freightTemplateChargeList;

}
