package com.dudu.weixin.shopweiixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.basedata.shopinfo.api.ApiBaseDataShopInfo;
import com.dudu.soa.basedata.shopinfo.module.ShopInfoParam;
import com.dudu.soa.customercenter.customer.api.ApiCustomerInfo;
import com.dudu.soa.customercenter.customer.module.CustomerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 冯广祥
 * 车管家微信客户注册
 * 2017/8/23.
 */
@Service
public class ShopRegisterService {
    /**
     * 打印日志
     */
    private static Logger logger = LoggerFactory.getLogger(ShopRegisterService.class);
    /**
     * 客户信息接口
     */
    @Reference
    private ApiCustomerInfo apiCustomerInfo;

    /**
     * 查询店铺关联关系
     */
    @Reference
    private ApiBaseDataShopInfo apiBaseDataShopInfo;

    /**
     * 冯广祥
     * 车管家注册
     *
     * @param shopCode 店铺编码
     * @param request  传参
     * @return 注册状态是否成功
     */
    public String register(String shopCode, HttpServletRequest request) {
        String customerName = request.getParameter("customerName");
        String plateNumber = request.getParameter("plateNumber");
        String mobilePhone = request.getParameter("mobilePhone");
        try {
            ShopInfoParam shopInfoParam = new ShopInfoParam();
            shopInfoParam.setShopCode(shopCode);
            //  查询主店铺shopCode
            String mianShopCode = apiBaseDataShopInfo.getMainShopCode(shopInfoParam);
            CustomerInfo param = new CustomerInfo();
            param.setCreateUserId(25)
                    .setCustomerName(customerName)
                    .setPlateNumber(plateNumber)
                    .setMainShopcode(mianShopCode)
                    .setShopCode(shopCode)
                    .setMobilePhone(mobilePhone);
            apiCustomerInfo.addCustomer(param);
            return "注册成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
