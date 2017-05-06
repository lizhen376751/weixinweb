package com.dudu.weixin.shopweiixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.customercenter.customerdemand.api.ApiCustomerDemand;
import com.dudu.soa.customercenter.customerdemand.model.QueryCustomerDemandParam;
import com.dudu.soa.customercenter.customerdemand.model.ResultQueryCustomerDemand;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 保养提醒
 * Created by lizhen on 2017/5/6.
 */
@Service
public class ShopBaoYangTiXing {
    /**
     * 引入查询客户需求接口(保养提醒)
     */
    @Reference(version = "1.0")
    private ApiCustomerDemand apiCustomerDemand;

    /**
     * 客户需求查询(保养提醒)
     *
     * @param shopcode    店铺编码
     * @param platenumber 车牌号码
     * @return 保养提醒列表
     */
    public List<ResultQueryCustomerDemand> queryBaoYangTiXing(String shopcode, String platenumber) {
        QueryCustomerDemandParam queryCustomerDemandParam = new QueryCustomerDemandParam();
        queryCustomerDemandParam.setCarHaoPai(platenumber);
        queryCustomerDemandParam.setShopCode(shopcode);
        List<ResultQueryCustomerDemand> resultQueryCustomerDemands = apiCustomerDemand.queryCustomerDemand(queryCustomerDemandParam);
        if (resultQueryCustomerDemands.size() > 0) {
//            for(String str:strs){
//                System.out.println(str);
//            }
//            for (){
//
//            }
        }
//        updateUserName
        return resultQueryCustomerDemands;
    }
}
