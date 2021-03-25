package com.shuaibi.shop.shop.entity.request;

import com.shuaibi.shop.common.entity.QueryRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: jianyufeng
 * @date: 2021/2/18 11:07
 * @description:
 */
@Data
public class GetProductListRequest extends QueryRequest {
    @NotNull(message = "店铺ID不能为空")
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "商品类目")
    private Long productCategoryId;

    @ApiModelProperty(value = "上架状态：0->下架；1->上架")
    private Boolean publishStatus;
}
