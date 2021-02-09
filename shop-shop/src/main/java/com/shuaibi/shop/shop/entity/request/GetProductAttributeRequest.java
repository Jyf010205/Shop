package com.shuaibi.shop.shop.entity.request;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: jianyufeng
 * @date: 2021/2/9 10:26
 * @description:
 */
@Data
public class GetProductAttributeRequest {
    @ApiModelProperty(value = "类目ID")
    private Long categoryId;

    @ApiModelProperty(value = "属性的类型；1->规格；2->参数（规格作为SKU 属性）")
    private Integer type;
}
