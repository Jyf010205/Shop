package com.shuaibi.shop.application.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaibi.shop.common.entity.table.PmsProductCategory;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-25
 */
public interface IPmsProductCategoryService extends IService<PmsProductCategory> {

    List<Tree<Long>> getCategoryTree();

}
