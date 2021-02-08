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
 * 
 * </p>
 *
 * @author syq
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PmsProductCategory对象", description="")
public class PmsProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "CATEGORY_ID", type = IdType.AUTO)
    private Long categoryId;

    @ApiModelProperty(value = "类目名称")
    @TableField("CATEGORY_NAME")
    private String categoryName;

    @ApiModelProperty(value = "上机分类的编号：0表示一级分类")
    @TableField("PARENTS_ID")
    private Long parentsId;

    @ApiModelProperty(value = "类目级别")
    @TableField("LEVEL")
    private Integer level;

    @ApiModelProperty(value = "是否显示在导航栏：0->不显示；1->显示")
    @TableField("NAV_STATUS")
    private Integer navStatus;

    @ApiModelProperty(value = "显示状态：0->不显示；1->显示")
    @TableField("SHOW_STATUS")
    private Integer showStatus;

    @ApiModelProperty(value = "排序顺序")
    @TableField("SORT")
    private Long sort;

    @ApiModelProperty(value = "图标")
    @TableField("ICON")
    private String icon;

    @ApiModelProperty(value = "描述")
    @TableField("DESCRIPTION")
    private String description;


}
