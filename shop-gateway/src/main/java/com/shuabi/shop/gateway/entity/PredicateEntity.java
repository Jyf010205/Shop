package com.shuabi.shop.gateway.entity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: shuaibishop
 * @Package: com.shuabi.shop.geteway.entity
 * @Description: note
 * @Author: SYQ
 * @CreateDate: 2021/1/21 15:12
 * @UpdateUser: SYQ
 * @UpdateDate: 2021/1/21 15:12
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * 路由断言实体类
 **/
public class PredicateEntity {

    //断言对应的Name
    private String name;

    //断言规则
    private Map<String, String> args = new LinkedHashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }
}
