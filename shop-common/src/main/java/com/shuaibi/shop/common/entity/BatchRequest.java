package com.shuaibi.shop.common.entity;

import cn.hutool.json.JSONArray;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpMethod;

/**
 * @author: jianyufeng
 * @date: 2021/1/19 15:50
 * @description: 批量操作封装类
 */
@Data
public class BatchRequest {
    @ApiModelProperty(value = "批量操作方法")
    private HttpMethod method;

    @ApiModelProperty(value = "批量操作请求体")
    private JSONArray body;
}
