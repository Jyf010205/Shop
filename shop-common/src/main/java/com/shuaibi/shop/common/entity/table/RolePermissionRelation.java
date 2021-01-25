package com.shuaibi.shop.common.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


/**
 * @author: jianyufeng
 * @date: 2021/1/13 15:38
 * @description: 角色和权限关系表
 */
@Data
@TableName("ums_role_permission_relation")
public class RolePermissionRelation implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long roleId;

    private Long PERMISSIONID;

    private static final long serialVersionUID = 1L;
}