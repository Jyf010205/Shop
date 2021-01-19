package com.shuaibi.shop.auth.entity.request;

import com.shuaibi.shop.common.entity.QueryRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: jianyufeng
 * @date: 2021/1/19 14:44
 * @description:
 */
@Data
public class FindUserListRequest extends QueryRequest {
    @ApiModelProperty(value = "用户名")
    private String username;
}
