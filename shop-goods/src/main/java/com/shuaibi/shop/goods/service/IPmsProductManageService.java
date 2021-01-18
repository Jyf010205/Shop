package com.shuaibi.shop.goods.service;

import com.shuaibi.shop.common.entity.table.PmsProductManage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syq
 * @since 2021-01-15
 */
public interface IPmsProductManageService extends IService<PmsProductManage> {
    List<PmsProductManage> searchProduct(PmsProductManage pmsProductManage);
}
