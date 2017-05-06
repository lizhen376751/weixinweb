package com.dudu.weixin.shopweiixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.customercenter.customer.api.ApiCustomerInfo;
import com.dudu.soa.customercenter.customer.module.CustomerInfo;
import com.dudu.soa.customercenter.customer.module.CustomerInfoParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 店管家的客户中心
 * Created by lizhen on 2017/5/6.
 */
@Service
public class ShopCustomInfo {
    /**
     * 引入店管家的客户中心接口
     */
    @Reference(version = "0.0.1")
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
}
