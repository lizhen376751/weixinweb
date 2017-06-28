package com.dudu.weixin.shopweiixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.dudu.soa.customercenter.customer.module.CustomerInfo;
import com.dudu.soa.evaluationsystem.api.ApiEvaluate;
import com.dudu.soa.evaluationsystem.module.EvaluateParam;
import com.dudu.soa.evaluationsystem.module.EvaluateReturn;
import com.dudu.soa.salescenter.bills.api.ApiBills;
import com.dudu.soa.salescenter.bills.module.BillsDetailParam;
import com.dudu.soa.salescenter.bills.module.ResultBillsDetail;
import com.dudu.soa.salescenter.xfjl.api.ApiPurchaseHistoryIntf;
import com.dudu.soa.salescenter.xfjl.module.QueryParamPurchaseHistory;
import com.dudu.soa.salescenter.xfjl.module.ResultPurchaseHistory;
import com.dudu.weixin.mould.PageResult;
import com.dudu.weixin.shopweiixin.mould.RecordsConsumption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 店管家微信的消费记录
 * Created by lizhen on 2017/5/6.
 */
@Service
public class ShopXiaoFeiJiLuService {
    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(ShopXiaoFeiJiLuService.class);
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
     * 引入消费评价接口
     */
    @Reference
    private ApiEvaluate apiEvaluate;

    /**
     * 店管家消费记录
     *
     * @param mainShopCode 店铺编码
     * @param plateNumber  车牌号
     * @return 消费列表
     */
    public PageResult<RecordsConsumption> queryXiaoFeiJiLu(String mainShopCode, String plateNumber) {
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
            EvaluateParam evaluateParam = new EvaluateParam();
            evaluateParam.setPlatenumber(plateNumber);
            evaluateParam.setShopCode(shopCode);
            evaluateParam.setWxPingZheng(wxpingzheng);
            List<EvaluateParam> list = apiEvaluate.queryListEvaluateParam(evaluateParam);
            //TODO 查询是否评价,然后将参数放入数据
            if (null != list && list.size() > 0) {
                EvaluateParam evaluateParam1 = list.get(0);
                Boolean evaluateIs = evaluateParam1.getEvaluateIs();
                recordsConsumption.setIsevaluate(evaluateIs);
            }
            recordsConsumption.setCustomId(id);
            recordsConsumption.setPlateNumber(plateNumber);
            recordsConsumptions.add(recordsConsumption);
        }
        rageResult.setRows(recordsConsumptions);
        return rageResult;
    }


    /**
     * 点击去评价时查询消费单据详情
     *
     * @param request 请求
     * @return 单据详情
     */
    public ResultBillsDetail getBillsDetail(HttpServletRequest request) {
        String shopcode = request.getParameter("shopCode");
        String wxpingzheng = request.getParameter("wxpingzheng");
        String customId = request.getParameter("customId");
        Integer id = null;
        if (null != customId && !"".equals(customId) && !"null".equals(customId)) {
            id = Integer.parseInt(customId);
        }
        BillsDetailParam billsDetailParam = new BillsDetailParam()
                .setShopCode(shopcode)
                .setCustomId(id)
                .setPingZheng(wxpingzheng);
        ResultBillsDetail billsDetail = apiBills.getBillsDetail(billsDetailParam);
        return billsDetail;
    }

    /**
     * 新增评价
     *
     * @param request 请求
     * @return 新增的id
     */
    public Integer addEvaluate(HttpServletRequest request) {
        String aa = request.getParameter("aa");
        JSONObject jsonObject = JSONObject.parseObject(aa);
        EvaluateReturn evaluateReturn = JSONObject.toJavaObject(jsonObject, EvaluateReturn.class);
        log.info("实体类转换为========" + evaluateReturn);
//        List<EvaluateCommodity> evaluateCommodities = evaluateReturn.getEvaluateCommodities();
//        log.info("商品评价========" + evaluateCommodities);
//        EvaluateBill evaluateBill = evaluateReturn.getEvaluateBill();
//        log.info("单据详情========" + evaluateBill);
//        EvaluateMain evaluateMain = evaluateReturn.getEvaluateMain();
//        log.info("主单据详情========" + evaluateMain);
        Integer integer = apiEvaluate.addEvaluate(evaluateReturn);
        return integer;
    }

    /**
     * 查看消费评价
     *
     * @param request 请求
     * @return 评价的实体类
     */
    public EvaluateParam getEvaluateParam(HttpServletRequest request) {
        String shopcode = request.getParameter("shopcode");
        String wxpingzheng = request.getParameter("wxpingzheng");
        String plateNumber = request.getParameter("plateNumber");
        EvaluateParam evaluateParam = new EvaluateParam();
        evaluateParam.setPlatenumber(plateNumber);
        evaluateParam.setShopCode(shopcode);
        evaluateParam.setWxPingZheng(wxpingzheng);
        List<EvaluateParam> list = apiEvaluate.queryListEvaluateParam(evaluateParam);
        if (list != null && list.size() > 0) {
            EvaluateParam evaluateParam1 = list.get(0);
            return evaluateParam1;
        }
        return null;
    }

}
