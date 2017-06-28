package com.dudu.weixin.shopweiixin.mould;

import com.dudu.soa.salescenter.xfjl.module.ResultPurchaseHistory;

import java.io.Serializable;

/**
 * 消费记录数据组装
 * Created by lizhen on 2017/5/22.
 */

public class RecordsConsumption implements Serializable {
    /**
     * 根据消费记录接口查询出来的
     * 查询出来的消费记录实体
     */
    private ResultPurchaseHistory resultPurchaseHistory;
    /**
     * 根据评价接口查询出来的是否进行了评价
     */
    private Boolean isevaluate;
    /**
     * 客户id
     */
    private Integer customId;
    /**
     * 车牌号码
     */
    private String plateNumber;

    /**
     *  RecordsConsumption(消费记录数据组装) 字符串形式
     * @return RecordsConsumption(消费记录数据组装)字符串
     */
    @Override
    public String toString() {
        return "resultPurchaseHistory:" + resultPurchaseHistory + ",isevaluate:" + isevaluate + ",customId:" + customId + ",plateNumber:" + plateNumber;
    }

    /**
     * 获取 根据消费记录接口查询出来的      查询出来的消费记录实体
     * @return resultPurchaseHistory 根据消费记录接口查询出来的      查询出来的消费记录实体
     */
    public ResultPurchaseHistory getResultPurchaseHistory() {
        return this.resultPurchaseHistory;
    }

    /**
     * 设置 根据消费记录接口查询出来的      查询出来的消费记录实体
     * @param resultPurchaseHistory 根据消费记录接口查询出来的      查询出来的消费记录实体
     * @return 返回 RecordsConsumption(消费记录数据组装)
     */
    public RecordsConsumption setResultPurchaseHistory(ResultPurchaseHistory resultPurchaseHistory) {
        this.resultPurchaseHistory = resultPurchaseHistory;
        return this;
    }

    /**
     * 获取 根据评价接口查询出来的是否进行了评价
     * @return isevaluate 根据评价接口查询出来的是否进行了评价
     */
    public Boolean getIsevaluate() {
        return this.isevaluate;
    }

    /**
     * 设置 根据评价接口查询出来的是否进行了评价
     * @param isevaluate 根据评价接口查询出来的是否进行了评价
     * @return 返回 RecordsConsumption(消费记录数据组装)
     */
    public RecordsConsumption setIsevaluate(Boolean isevaluate) {
        this.isevaluate = isevaluate;
        return this;
    }

    /**
     * 获取 客户id
     * @return customId 客户id
     */
    public Integer getCustomId() {
        return this.customId;
    }

    /**
     * 设置 客户id
     * @param customId 客户id
     * @return 返回 RecordsConsumption(消费记录数据组装)
     */
    public RecordsConsumption setCustomId(Integer customId) {
        this.customId = customId;
        return this;
    }

    /**
     * 获取 车牌号码
     * @return plateNumber 车牌号码
     */
    public String getPlateNumber() {
        return this.plateNumber;
    }

    /**
     * 设置 车牌号码
     * @param plateNumber 车牌号码
     * @return 返回 RecordsConsumption(消费记录数据组装)
     */
    public RecordsConsumption setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }
}
