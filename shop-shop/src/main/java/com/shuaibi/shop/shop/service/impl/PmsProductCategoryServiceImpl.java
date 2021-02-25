package com.shuaibi.shop.shop.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.shuaibi.shop.common.entity.table.PmsProductCategory;
import com.shuaibi.shop.common.mapper.PmsProductCategoryMapper;
import com.shuaibi.shop.shop.service.IPmsProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-25
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements IPmsProductCategoryService {

    /**
     * 获取类目树
     * @return
     */
    @Override
    public List<Tree<Long>> getCategoryTree() {
        List<PmsProductCategory> productCategoryList = this.list();
        //转换树结构
        List<TreeNode<Long>> treeNodes = productCategoryList.stream().map(category -> {
            Map<String, Object> extra = new HashMap<>();
            extra.put("icon",category.getIcon());
            extra.put("description",category.getDescription());
            return new TreeNode<Long>()
                    .setId(category.getCategoryId())
                    .setParentId(category.getParentsId())
                    .setName(category.getCategoryName())
                    .setExtra(extra);
        }).collect(Collectors.toList());
        return TreeUtil.build(treeNodes, 0L);
    }
}
