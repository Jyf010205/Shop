package com.shuaibi.shop.common.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.shuaibi.shop.common.annotation.Mobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 会员收货地址表
 * </p>
 *
 * @author syq
 * @since 2021-01-21
 */
@Data
@TableName("ums_user_receive_address")
@ApiModel(value="ReceiveAddress对象", description="会员收货地址表")
public class ReceiveAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @TableField("USER_ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    @ApiModelProperty(value = "收货人名称")
    @NotBlank(message = "收货人姓名不能为空")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "手机号")
    @TableField("MOBILE")
    @NotNull(message = "手机号不能为空")
    @Mobile(message = "手机号格式不正确")
    private Long mobile;

    @ApiModelProperty(value = "是否为默认")
    @TableField("DEFAULT_STATUS")
    private Boolean defaultStatus;

    @ApiModelProperty(value = "邮政编码")
    @TableField("POST_CODE")
    private Long postCode;

    @ApiModelProperty(value = "省份/直辖市")
    @TableField("PROVINCE")
    @NotBlank(message = "省份/直辖市不能为空")
    private String province;

    @ApiModelProperty(value = "城市")
    @TableField("CITY")
    @NotBlank(message = "城市不能为空")
    private String city;

    @ApiModelProperty(value = "区")
    @TableField("REGION")
    @NotBlank(message = "区不能为空")
    private String region;

    @ApiModelProperty(value = "详细地址(街道)")
    @TableField("DETAIL_ADDRESS")
    @NotBlank(message = "详细地址不能为空")
    private String detailAddress;


}
