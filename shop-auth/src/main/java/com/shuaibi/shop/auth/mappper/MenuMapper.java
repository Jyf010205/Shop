package com.shuaibi.shop.auth.mappper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuaibi.shop.common.entity.table.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    @Select("select distinct m.perms\n" +
            "        from t_role r\n" +
            "                 left join t_user u on (u.ROLE_ID = r.ROLE_ID)\n" +
            "                 left join t_role_menu rm on (rm.role_id = r.role_id)\n" +
            "                 left join t_menu m on (m.menu_id = rm.menu_id)\n" +
            "        where u.username = #{userName}\n" +
            "          and m.perms is not null")
    List<Menu> findUserPermissions(String username);

    @Select("select *\n" +
            "        from t_role r\n" +
            "                 left join t_user u on (u.ROLE_ID = r.ROLE_ID)\n" +
            "                 left join t_role_menu rm on (rm.role_id = r.role_id)\n" +
            "                 left join t_menu m on (m.menu_id = rm.menu_id)\n" +
            "        where u.username = #{userName}")
    List<Menu> findUserMenus(String username);
}
