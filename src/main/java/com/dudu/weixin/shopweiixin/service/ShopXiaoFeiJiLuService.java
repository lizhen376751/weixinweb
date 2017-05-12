package com.dudu.weixin.shopweiixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.customercenter.customer.module.CustomerInfo;
import com.dudu.soa.salescenter.xfjl.api.ApiPurchaseHistoryIntf;
import com.dudu.soa.salescenter.xfjl.module.QueryParamPurchaseHistory;
import com.dudu.soa.salescenter.xfjl.module.ResultPurchaseHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 店管家微信的消费记录
 * Created by lizhen on 2017/5/6.
 */
@Service
public class ShopXiaoFeiJiLuService {
    /**
     * 引入消费记录接口
     */
    @Reference(version = "0.0.1")
    private ApiPurchaseHistoryIntf apiPurchaseHistoryIntf;
    /**
     * 引入店管家客户中心
     */
    @Autowired
    private ShopCustomInfoService shopCustomInfo;

    /**
     * 店管家消费记录
     *
     * @param mainShopCode 店铺编码
     * @param plateNumber  车牌号
     * @return 消费列表
     */
    public List<ResultPurchaseHistory> queryXiaoFeiJiLu(String mainShopCode, String plateNumber) {
        CustomerInfo customerInfo = shopCustomInfo.queryCustomerList(mainShopCode, plateNumber);
        Integer id = null;
        if (customerInfo != null) {
            id = customerInfo.getId();
        }
        QueryParamPurchaseHistory queryParamPurchaseHistory = new QueryParamPurchaseHistory();
        queryParamPurchaseHistory.setShopCode(mainShopCode);
        queryParamPurchaseHistory.setCustomerId(id);
        List<ResultPurchaseHistory> resultPurchaseHistories = apiPurchaseHistoryIntf.queryPurchaseHistory(queryParamPurchaseHistory);
        return resultPurchaseHistories;
    }

}
