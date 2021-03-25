package com.shuaibi.shop.application.entity.request;

import com.shuaibi.shop.common.entity.QueryRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: shuaibishop
 * @Package: com.shuaibi.shop.application.entity.request
 * @Description: note
 * @Author: SYQ
 * @CreateDate: 2021/2/7 11:18
 * @UpdateUser: SYQ
 * @UpdateDate: 2021/2/7 11:18
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2021 Hundsun Technologies Inc. All Rights Reserved
 **/
@Data
public class GetProductListRequest extends QueryRequest {
    @ApiModelProperty(value = "查询索引关键字")
    private String queryIndex;

    @ApiModelProperty(value = "商品类目")
    private Long productCategoryId;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "销量,true->asc,false->desc")
    private Boolean sales;

    @ApiModelProperty(value = "售价,true->asc,false->desc")
    private Boolean price;

}
