package com.shuaibi.shop.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: jianyufeng
 * @date: 2021/1/19 14:40
 * @description: 分页查询请求封装
 */
@Data
public class QueryRequest{
    @ApiModelProperty(value = "当前页面数据量")
    private int pageSize = 10;

    @ApiModelProperty(value = "当前页码")
    private int pageNum = 1;

    @ApiModelProperty(value = "排序字段")
    private String field;

    @ApiModelProperty(value = "排序规则")
    private Boolean order = true;
}
