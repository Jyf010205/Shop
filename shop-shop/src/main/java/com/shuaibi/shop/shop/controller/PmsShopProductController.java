package com.shuaibi.shop.shop.controller;


import com.shuaibi.shop.shop.service.IPmsShopService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商城商品信息表
 * </p>
 *
 * @author jianyufeng
 * @since 2021-02-01
 */
@RestController
@RequestMapping("/product")
@Api(tags = "店铺商品Api")
public class PmsShopProductController {
    @Autowired
    IPmsShopService pmsShopService;

}
