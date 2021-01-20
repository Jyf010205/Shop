package com.shuaibi.shop.personal.servie;

import com.shuaibi.shop.common.entity.table.User;

/**
 * @author: jianyufeng
 * @date: 2021/1/20 22:46
 * @description:
 */
public interface IPersonalService {
    User getPersonalInfo(String userId);
}
