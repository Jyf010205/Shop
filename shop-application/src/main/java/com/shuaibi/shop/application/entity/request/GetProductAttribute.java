package com.shuaibi.shop.application.entity.request;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @CreateDate: 2021/2/8 11:24
 * @UpdateUser: SYQ
 * @UpdateDate: 2021/2/8 11:24
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2021 Hundsun Technologies Inc. All Rights Reserved
 **/
@Data
public class GetProductAttribute {

    @ApiModelProperty(value = "类目ID")
    private Long categoryId;

}
