package com.shuaibi.shop.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuaibi.shop.common.entity.table.Permission;
import com.shuaibi.shop.common.entity.table.UserRoleRelation;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: jianyufeng
 * @date: 2021/1/13 16:45
 * @description:
 */
public interface UserRoleRelationMapper extends BaseMapper<UserRoleRelation> {

    /**
     * 获取用户权限
     * @param userId
     * @return
     */
    @Select("SELECT\n" +
            "            p.*\n" +
            "        FROM\n" +
            "            t_user_role_relation ar\n" +
            "            LEFT JOIN t_role r ON ar.role_id = r.id\n" +
            "            LEFT JOIN t_role_permission_relation rp ON r.id = rp.role_id\n" +
            "            LEFT JOIN t_permission p ON rp.permission_id = p.id\n" +
            "        WHERE\n" +
            "            ar.user_id = #{userId}\n" +
            "            AND p.id IS NOT NULL\n" +
            "            AND p.id NOT IN (\n" +
            "                SELECT\n" +
            "                    p.id\n" +
            "                FROM\n" +
            "                    t_user_permission_relation pr\n" +
            "                    LEFT JOIN t_permission p ON pr.permission_id = p.id\n" +
            "                WHERE\n" +
            "                    pr.type = - 1\n" +
            "                    AND pr.user_id = #{userId}\n" +
            "            )\n" +
            "        UNION\n" +
            "        SELECT\n" +
            "            p.*\n" +
            "        FROM\n" +
            "            t_user_permission_relation pr\n" +
            "            LEFT JOIN t_permission p ON pr.permission_id = p.id\n" +
            "        WHERE\n" +
            "            pr.type = 1\n" +
            "            AND pr.user_id = #{userId}")
    List<Permission> getPermissionList(Long userId);
}
