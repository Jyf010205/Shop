package com.shuaibi.shop.common.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: jianyufeng
 * @date: 2021/1/13 15:38
 * @description: 用户和角色关系表
 */
@Data
@TableName("t_user_permission_relation")
public class UserRoleRelation implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long adminId;

    private Long roleId;

    private static final long serialVersionUID = 1L;

}