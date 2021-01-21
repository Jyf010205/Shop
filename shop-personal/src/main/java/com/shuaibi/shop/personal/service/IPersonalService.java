package com.shuaibi.shop.personal.service;

import com.shuaibi.shop.common.entity.table.User;
import com.shuaibi.shop.personal.entity.request.ModifyPasswordRequest;

/**
 * @author: jianyufeng
 * @date: 2021/1/20 22:46
 * @description:
 */
public interface IPersonalService {
    User getPersonalInfo(String userId);

    Integer update(User user, String userId);

    Integer modifyPassword(ModifyPasswordRequest request, String userId);
}
