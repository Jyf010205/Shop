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
 * 产品参数表
 * </p>
 *
 * @author syq
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PmsProductAttribute对象", description="产品参数表")
public class PmsProductAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ATTRIBUTE_ID", type = IdType.AUTO)
    private Long attributeId;

    @ApiModelProperty(value = "类目ID")
    @TableField("CATEGORY_ID")
    private Long categoryId;

    @ApiModelProperty(value = "产品属性名称")
    @TableField("ATTRIBUTE_NAME")
    private String attributeName;

    @ApiModelProperty(value = "属性的类型；1->规格；2->参数（规格作为SKU 属性）")
    @TableField("TYPE")
    private Integer type;

    @ApiModelProperty(value = "属性选择类型：1->手动输入；2->单选；3->多选")
    @TableField("SELECT_TYPE")
    private Integer selectType;

    @ApiModelProperty(value = "可选值列表，逗号分开")
    @TableField("INPUT_LIST")
    private String inputList;

    @ApiModelProperty(value = "检索类型；1->不需要进行检索；2->关键字检索；3->范围检索")
    @TableField("SEARCH_TYPE")
    private Integer searchType;


}
