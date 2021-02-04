package com.shuaibi.shop.shop.entity.request;

import com.shuaibi.shop.common.entity.QueryRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: jianyufeng
 * @date: 2021/2/4 17:51
 * @description:
 */
@Data
public class GetFansListRequest extends QueryRequest {
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
}
