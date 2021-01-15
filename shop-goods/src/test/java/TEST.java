import com.shuaibi.shop.common.entity.table.PmsProductManage;
import com.shuaibi.shop.common.mapper.PmsProductManageMapper;
import com.shuaibi.shop.goods.ShopGoodsApplication;
import com.shuaibi.shop.goods.service.IPmsProductManageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: shuaibishop
 * @Package: PACKAGE_NAME
 * @Description: note
 * @Author: SYQ
 * @CreateDate: 2021/1/14 17:17
 * @UpdateUser: SYQ
 * @UpdateDate: 2021/1/14 17:17
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2021 Hundsun Technologies Inc. All Rights Reserved
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopGoodsApplication.class)
public class TEST {
    @Autowired
    private PmsProductManageMapper pmsProductManageMapper;
    @Autowired
    private IPmsProductManageService iPmsProductManageService;

    @Test
    public void test() {
        PmsProductManage pmsProductManage = pmsProductManageMapper.selectById(1);
        System.out.println(pmsProductManage);
        System.out.println("------------------------------------");
//        PmsProductManage pmsProductManage1 = iPmsProductManageService.selectAll();
//        System.out.println(pmsProductManage1);
    }
}
