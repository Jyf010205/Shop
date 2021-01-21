package com.shuabi.shop.geteway.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: shuaibishop
 * @Package: com.shuabi.shop.geteway.entity
 * @Description: note
 * @Author: SYQ
 * @CreateDate: 2021/1/21 15:10
 * @UpdateUser: SYQ
 * @UpdateDate: 2021/1/21 15:10
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * 路由实体类
 **/
@Data
public class RouteEntity {

    //路由id
    private String id;

    //路由断言集合
    private List<PredicateEntity> predicates = new ArrayList<>();

    //路由过滤器集合
    private List<FilterEntity> filters = new ArrayList<>();

    //路由转发的目标uri
    private String uri;

    //路由执行的顺序
    private int order = 0;

}
