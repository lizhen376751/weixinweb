package com.dudu.weixin.mould;

import com.dudu.soa.salescenter.bills.module.ResultBillsDetail;
import com.dudu.soa.salescenter.shoporder.module.ShopOrderParam;
import com.dudu.soa.salescenter.workcomplate.module.EdbWorkComplateMessage;

import java.io.Serializable;
import java.util.List;

/**
 * 自助开单相关的参数返回
 * Created by lizhen on 2017/8/19.
 */
public class BillsDetail implements Serializable {
    /**
     * 施工步骤
     */
    private List<EdbWorkComplateMessage> edbWorkComplateMessageList;
    /**
     * 第一次自助开单的相关信息(第一次开单之后返回的开单信息)
     */
    private ShopOrderParam shopOrderParam;
    /**
     * 再次进入查询工单信息(调用查询工单信息接口)
     */
    private ResultBillsDetail resultBillsDetail;
    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     *  BillsDetail(自助开单相关的参数返回) 字符串形式
     * @return BillsDetail(自助开单相关的参数返回)字符串
     */
    @Override
    public String toString() {
        return "edbWorkComplateMessageList:" + edbWorkComplateMessageList + ",shopOrderParam:" + shopOrderParam + ",resultBillsDetail:" + resultBillsDetail
                + ",plateNumber:" + plateNumber;
    }

    /**
     * 获取 施工步骤
     * @return edbWorkComplateMessageList 施工步骤
     */
    public List<EdbWorkComplateMessage> getEdbWorkComplateMessageList() {
        return this.edbWorkComplateMessageList;
    }

    /**
     * 设置 施工步骤
     * @param edbWorkComplateMessageList 施工步骤
     * @return 返回 BillsDetail(自助开单相关的参数返回)
     */
    public BillsDetail setEdbWorkComplateMessageList(List<EdbWorkComplateMessage> edbWorkComplateMessageList) {
        this.edbWorkComplateMessageList = edbWorkComplateMessageList;
        return this;
    }

    /**
     * 获取 第一次自助开单的相关信息(第一次开单之后返回的开单信息)
     * @return shopOrderParam 第一次自助开单的相关信息(第一次开单之后返回的开单信息)
     */
    public ShopOrderParam getShopOrderParam() {
        return this.shopOrderParam;
    }

    /**
     * 设置 第一次自助开单的相关信息(第一次开单之后返回的开单信息)
     * @param shopOrderParam 第一次自助开单的相关信息(第一次开单之后返回的开单信息)
     * @return 返回 BillsDetail(自助开单相关的参数返回)
     */
    public BillsDetail setShopOrderParam(ShopOrderParam shopOrderParam) {
        this.shopOrderParam = shopOrderParam;
        return this;
    }

    /**
     * 获取 再次进入查询工单信息(调用查询工单信息接口)
     * @return resultBillsDetail 再次进入查询工单信息(调用查询工单信息接口)
     */
    public ResultBillsDetail getResultBillsDetail() {
        return this.resultBillsDetail;
    }

    /**
     * 设置 再次进入查询工单信息(调用查询工单信息接口)
     * @param resultBillsDetail 再次进入查询工单信息(调用查询工单信息接口)
     * @return 返回 BillsDetail(自助开单相关的参数返回)
     */
    public BillsDetail setResultBillsDetail(ResultBillsDetail resultBillsDetail) {
        this.resultBillsDetail = resultBillsDetail;
        return this;
    }

    /**
     * 获取 车牌号
     * @return plateNumber 车牌号
     */
    public String getPlateNumber() {
        return this.plateNumber;
    }

    /**
     * 设置 车牌号
     * @param plateNumber 车牌号
     * @return 返回 BillsDetail(自助开单相关的参数返回)
     */
    public BillsDetail setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }
}
