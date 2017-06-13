package com.dudu.weixin.shopweiixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.customercenter.customer.api.ApiCustomerInfo;
import com.dudu.soa.customercenter.customer.module.CustomerInfo;
import com.dudu.soa.customercenter.customer.module.CustomerInfoParam;
import com.dudu.soa.customercenter.customer.module.UpdateCustomerInfoParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 店管家的客户中心
 * Created by lizhen on 2017/5/6.
 */
@Service
public class ShopCustomInfoService {
    /**
     * 引入店管家的客户中心接口
     */
    @Reference(timeout = 30000)
    private ApiCustomerInfo apiCustomerInfo;

    /**
     * 车管家微信的用户登录时进行验证
     *
     * @param mainShopCode 主店铺id
     * @param plateNumber  车牌号
     * @return 客户信息的集合
     */
    public CustomerInfo queryCustomerList(String mainShopCode, String plateNumber) {
        CustomerInfoParam customerInfoParam = new CustomerInfoParam()
                .setMainShopCode(mainShopCode)
                .setPlateNumber(plateNumber);
        List<CustomerInfo> customerInfos = apiCustomerInfo.queryCustomerList(customerInfoParam);
        if (customerInfos != null && customerInfos.size() > 0) {
            CustomerInfo customerInfo = customerInfos.get(0);
            return customerInfo;
        }
        return null;
    }

    /**
     * 修改店管家客户信息
     *
     * @param updateCustomerInfoParam 传入修改的参数
     * @return 受影响的行数
     */
    public Integer updateCustomerInfo(UpdateCustomerInfoParam updateCustomerInfoParam) {
//         id, mainShopCode, plateNumber,    carBrandId, carModelId, carSeriesId ,kilo
        int i = apiCustomerInfo.updateCustomer(updateCustomerInfoParam);
        return i;

    }
}
