package com.shuaibi.shop.application.entity.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class GetProductRequest extends QueryRequest {
    @ApiModelProperty(value = "商品编号")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long productId;
}
