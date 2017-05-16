package com.dudu.weixin.shopweiixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.customercenter.customerdemand.api.ApiCustomerDemand;
import com.dudu.soa.customercenter.customerdemand.model.QueryCustomerDemandParam;
import com.dudu.soa.customercenter.customerdemand.model.ResultQueryCustomerDemand;
import com.dudu.soa.weixindubbo.shopinfo.api.ApiShopInfo;
import com.dudu.soa.weixindubbo.shopinfo.module.ShopInfo;
import com.dudu.weixin.mould.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 保养提醒
 * Created by lizhen on 2017/5/6.
 */
@Service
public class ShopBaoYangTiXingService {
    /**
     * 引入店铺信息的接口
     */
    @Reference(version = "1.0")
    private ApiShopInfo apiShopInfo;
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
    public PageResult<ResultQueryCustomerDemand> queryBaoYangTiXing(String shopcode, String platenumber) {
        QueryCustomerDemandParam queryCustomerDemandParam = new QueryCustomerDemandParam();
        queryCustomerDemandParam.setCarHaoPai(platenumber);
        queryCustomerDemandParam.setShopCode(shopcode);
        List<ResultQueryCustomerDemand> resultQueryCustomerDemands = apiCustomerDemand.queryCustomerDemand(queryCustomerDemandParam);
        if (resultQueryCustomerDemands.size() > 0) {
            for (ResultQueryCustomerDemand resultQueryCustomerDemand : resultQueryCustomerDemands) {
                String shopCode = resultQueryCustomerDemand.getShopCode();
                ShopInfo shopInfo = apiShopInfo.getShopInfo(shopCode);
                String shopName = shopInfo.getShopName();
                resultQueryCustomerDemand.setUpdateUserName(shopName);
            }

        }
        PageResult rageResult = new PageResult<ResultQueryCustomerDemand>(resultQueryCustomerDemands);
        return rageResult;

    }
}
