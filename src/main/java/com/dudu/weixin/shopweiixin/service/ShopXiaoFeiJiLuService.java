package com.dudu.weixin.shopweiixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.customercenter.customer.module.CustomerInfo;
import com.dudu.soa.salescenter.bills.api.ApiBills;
import com.dudu.soa.salescenter.bills.module.BillsDetailParam;
import com.dudu.soa.salescenter.bills.module.ResultBillsDetail;
import com.dudu.soa.salescenter.xfjl.api.ApiPurchaseHistoryIntf;
import com.dudu.soa.salescenter.xfjl.module.QueryParamPurchaseHistory;
import com.dudu.soa.salescenter.xfjl.module.ResultPurchaseHistory;
import com.dudu.weixin.mould.PageResult;
import com.dudu.weixin.shopweiixin.mould.RecordsConsumption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Reference(timeout = 30000)
    private ApiPurchaseHistoryIntf apiPurchaseHistoryIntf;
    /**
     * 引入查询消费单据详情接口
     */
    @Reference
    private ApiBills apiBills;
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
    public  PageResult<RecordsConsumption> queryXiaoFeiJiLu(String mainShopCode, String plateNumber) {
        List<RecordsConsumption> recordsConsumptions = new ArrayList<RecordsConsumption>();
        CustomerInfo customerInfo = shopCustomInfo.queryCustomerList(mainShopCode, plateNumber);
        Integer id = null;
        if (customerInfo != null) {
            id = customerInfo.getId();
        }
        QueryParamPurchaseHistory queryParamPurchaseHistory = new QueryParamPurchaseHistory();
        queryParamPurchaseHistory.setShopCode(mainShopCode);
        queryParamPurchaseHistory.setCustomerId(id);
        List<ResultPurchaseHistory> resultPurchaseHistories = apiPurchaseHistoryIntf.queryPurchaseHistory(queryParamPurchaseHistory);
        PageResult rageResult = new PageResult<ResultPurchaseHistory>(resultPurchaseHistories);
        Long records = rageResult.getRecords();
        for (ResultPurchaseHistory resultPurchaseHistory : resultPurchaseHistories) {
            RecordsConsumption recordsConsumption = new RecordsConsumption();
            recordsConsumption.setResultPurchaseHistory(resultPurchaseHistory);
            String shopCode = resultPurchaseHistory.getShopCode();
            String wxpingzheng = resultPurchaseHistory.getWxpingzheng();
            //TODO 查询是否评价,然后将参数放入数据
            if (true) {
                recordsConsumption.setIsevaluate(true);
            }
            recordsConsumptions.add(recordsConsumption);
        }
        rageResult.setRows(recordsConsumptions);
        return rageResult;
    }

    /**
     * 点击评价时查询消费单据详情
     *
     * @param shopcode    店铺编码
     * @param plateNumber 车牌号
     * @param wxpingzheng 维修凭证
     * @return 单据详情阿
     */
    public ResultBillsDetail getBillsDetail(String shopcode, String plateNumber, String wxpingzheng) {
        CustomerInfo customerInfo = shopCustomInfo.queryCustomerList(shopcode, plateNumber);
        Integer id = null;
        if (customerInfo != null) {
            id = customerInfo.getId();
        }
        BillsDetailParam billsDetailParam = new BillsDetailParam()
                .setShopCode(shopcode)
                .setCustomId(id)
                .setPingZheng(wxpingzheng);
        ResultBillsDetail billsDetail = apiBills.getBillsDetail(billsDetailParam);
        return billsDetail;
    }


}
