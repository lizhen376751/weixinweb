package com.dudu.weixin.shopweiixin.mould;

import com.dudu.soa.salescenter.xfjl.module.ResultPurchaseHistory;

import java.io.Serializable;

/**
 * 消费记录数据组装
 * Created by lizhen on 2017/5/22.
 */

public class RecordsConsumption implements Serializable{
    /**
     * 根据消费记录接口查询出来的
     *查询出来的消费记录实体
     */
    private ResultPurchaseHistory resultPurchaseHistory;
    /**
     * 根据评价接口查询出来的是否进行了评价
     */
    private Boolean isevaluate;

    /**
     *  RecordsConsumption(消费记录数据组装) 字符串形式
     * @return RecordsConsumption(消费记录数据组装)字符串
     */
    @Override
    public String toString() {
        return "resultPurchaseHistory:" + resultPurchaseHistory + ",isevaluate:" + isevaluate;
    }

    /**
     * 获取 根据消费记录接口查询出来的     查询出来的消费记录实体
     * @return resultPurchaseHistory 根据消费记录接口查询出来的     查询出来的消费记录实体
     */
    public ResultPurchaseHistory getResultPurchaseHistory() {
        return this.resultPurchaseHistory;
    }

    /**
     * 设置 根据消费记录接口查询出来的     查询出来的消费记录实体
     * @param resultPurchaseHistory 根据消费记录接口查询出来的     查询出来的消费记录实体
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
}
