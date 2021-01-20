package com.shuaibi.shop.common.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.shuaibi.shop.common.entity.enums.ChannelType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: jianyufeng
 * @date: 2021/1/20 15:35
 * @description: 用户登陆记录表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_user_login_log")
public class UserLoginLog {
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "IP")
    private String ip;

    @ApiModelProperty(value = "登录类型")
    private ChannelType loginChannel;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
