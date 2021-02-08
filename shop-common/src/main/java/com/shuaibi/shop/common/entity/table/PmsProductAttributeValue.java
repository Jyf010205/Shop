package com.shuaibi.shop.common.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 存储产品参数数据表
 * </p>
 *
 * @author syq
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PmsProductAttributeValue对象", description="存储产品参数数据表")
public class PmsProductAttributeValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "产品ID")
    @TableField("PRODUCT_ID")
    private Long productId;

    @ApiModelProperty(value = "产品属性ID")
    @TableField("ATTRIBUTE_ID")
    private Long attributeId;

    @ApiModelProperty(value = "产品参数（参数单值，规格有多个逗号分开）")
    @TableField("ATTRIBUTE_VALUE")
    private String attributeValue;


}
