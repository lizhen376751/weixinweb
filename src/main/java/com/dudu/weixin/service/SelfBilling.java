package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.lmk.special.elb.api.ApiElbSpecialCustomerIntf;
import com.dudu.soa.lmk.special.elb.module.ElbCheckCustomerResult;
import com.dudu.soa.lmk.wxcustomer.module.WxCustomer;
import com.dudu.soa.ordercenter.shoporder.api.ApiShopOrderIntf;
import com.dudu.soa.ordercenter.shoporder.module.SpecialOrderQueryParam;
import com.dudu.soa.salescenter.shoporder.module.ShopOrderParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 联盟微信自助开单
 * Created by lizhen on 2017/8/16.
 */
@Service
public class SelfBilling {
    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(SelfBilling.class);
    /**
     * 引入联盟用户信息注册的服务
     */
    @Autowired
    private WxCustomerService wxCustomerService;
    /**
     * 联盟客户是否已经激活
     */
    @Reference
    private ApiElbSpecialCustomerIntf apiElbSpecialCustomerIntf;
    /**
     * 联盟微信开单接口
     */
    private ApiShopOrderIntf apiShopOrderIntf;

    /**
     * 判断盟客户是否已经激活
     *
     * @return true或者false
     */
    public ElbCheckCustomerResult checkCustomerIsActive() {
        WxCustomer wxCustomer = wxCustomerService.getWxCustomer("鲁A2032", "CS000");
        if (wxCustomer != null) {
            Integer id = wxCustomer.getId();
            long customerId = (long) id;
            ElbCheckCustomerResult elbCheckCustomerResult = apiElbSpecialCustomerIntf.checkCustomerIsActive(customerId);
            log.debug("联盟客户是否已经激活" + elbCheckCustomerResult.toString());
            return elbCheckCustomerResult;
        }
        return null;
    }

    /**
     * 创建工单
     *
     * @return 开单信息
     */
    public ShopOrderParam createDefaultShopOrder() {
        WxCustomer wxCustomer = wxCustomerService.getWxCustomer("鲁A2032", "CS000");
        if (wxCustomer != null) {
            Integer id = wxCustomer.getId();
            long customerId = (long) id;
            String brandCode = wxCustomer.getBrandCode();
            SpecialOrderQueryParam specialOrderQueryParam = new SpecialOrderQueryParam();
            specialOrderQueryParam.setBrandCode(brandCode);
            specialOrderQueryParam.setBrandCustomerId(customerId);
            ShopOrderParam defaultShopOrder4Elb = apiShopOrderIntf.createDefaultShopOrder4Elb(specialOrderQueryParam);
            log.debug("开单信息" + defaultShopOrder4Elb.toString());
            return defaultShopOrder4Elb;
        }
        return null;
    }

}
