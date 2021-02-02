package com.shuaibi.shop.shop.entity.request;

import com.shuaibi.shop.common.entity.QueryRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: jianyufeng
 * @date: 2021/2/2 10:47
 * @description:
 */
@Data
public class GetFreightTemplateRequest extends QueryRequest {
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
}
