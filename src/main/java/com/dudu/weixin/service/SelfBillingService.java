package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.lmk.special.elb.api.ApiElbSpecialCustomerIntf;
import com.dudu.soa.lmk.special.elb.module.CustomerSpecialSaveModule;
import com.dudu.soa.lmk.special.elb.module.ElbCheckCustomerResult;
import com.dudu.soa.lmk.wxcustomer.module.WxCustomer;
import com.dudu.soa.ordercenter.shoporder.api.ApiShopOrderIntf;
import com.dudu.soa.ordercenter.shoporder.module.SpecialOrderQueryParam;
import com.dudu.soa.salescenter.shoporder.module.ShopOrderParam;
import com.dudu.soa.salescenter.shoporder.module.ShopOrderProjectDetail;
import com.dudu.soa.salescenter.workcomplate.module.EdbWorkComplateMessage;
import com.dudu.weixin.shopweiixin.service.ShopShiGongBuZhouService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 联盟微信自助开单
 * Created by lizhen on 2017/8/16.
 */
@Service
public class SelfBillingService {
    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(SelfBillingService.class);
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
    @Reference
    private ApiShopOrderIntf apiShopOrderIntf;
    /**
     * 查看施工步骤
     */
    @Autowired
    private ShopShiGongBuZhouService shopShiGongBuZhou;

    /**
     * 判断盟客户是否已经激活
     *
     * @param cardId 联盟卡的id
     * @param lmcode 联盟编码
     * @return 是否已经激活
     */
    public ElbCheckCustomerResult checkCustomerIsActive(String cardId, String lmcode) {
        if (null != cardId && !"".equals(cardId) && !"null".equals(cardId)) {
            Long parseLong = new Long((long) Integer.parseInt(cardId));
            CustomerSpecialSaveModule customerSpecialSaveModule = new CustomerSpecialSaveModule().setCardId(parseLong).setBrandCode(lmcode);
            ElbCheckCustomerResult elbCheckCustomerResult = apiElbSpecialCustomerIntf.checkCardOrderIsCreate(customerSpecialSaveModule);
            log.debug("联盟客户是否已经激活" + elbCheckCustomerResult.toString());
            return elbCheckCustomerResult;
        }
        return null;
    }


    /**
     * 联盟卡创建工单,店铺的开单信息
     *
     * @param plateNumber 车牌号
     * @param lmcode      联盟编码
     * @param request     请求
     * @return 店铺的开单信息
     */
    public List<EdbWorkComplateMessage> createDefaultShopOrder(String plateNumber, String lmcode, HttpServletRequest request) {
        String cardId = request.getParameter("cardId"); //联盟卡id
        String cardNumber = request.getParameter("cardNumber"); //联盟卡的卡号
        WxCustomer wxCustomer = wxCustomerService.getWxCustomer(plateNumber, lmcode);
        if (wxCustomer != null) {
            // TODO 测试之后进行删除
            Integer id = 566;
//            Integer id = wxCustomer.getId();
            long customerId = (long) id;
            Long parseLong = new Long((long) Integer.parseInt(cardId));
            String brandCode = wxCustomer.getBrandCode();
            SpecialOrderQueryParam specialOrderQueryParam = new SpecialOrderQueryParam();
            specialOrderQueryParam.setBrandCode(brandCode);
            specialOrderQueryParam.setBrandCustomerId(customerId);
            specialOrderQueryParam.setBrandCardId(parseLong);
            specialOrderQueryParam.setBrandCardNumber(cardNumber);
            ShopOrderParam defaultShopOrder4Elb = apiShopOrderIntf.createDefaultShopOrder4Elb(specialOrderQueryParam);
            if (null != defaultShopOrder4Elb) {
                log.debug("开单信息" + defaultShopOrder4Elb.toString());
                //联盟卡自助激活 施工步骤
                List<ShopOrderProjectDetail> projectDetails = defaultShopOrder4Elb.getProjectDetails();
                if (projectDetails != null) {
                    ShopOrderProjectDetail shopOrderProjectDetail = projectDetails.get(0);
                    String itemCode = shopOrderProjectDetail.getItemCode();
                    String itemId = shopOrderProjectDetail.getItemId().toString();
                    String shopcode = shopOrderProjectDetail.getShopcode();
                    String wxpingzheng = shopOrderProjectDetail.getWxpingzheng();
                    List<EdbWorkComplateMessage> edbWorkComplateMessages = shopShiGongBuZhou.queryShiGongBuZhou(shopcode, wxpingzheng, itemCode, itemId);
                    return edbWorkComplateMessages;
                }
            }


        }
        return null;
    }

}
