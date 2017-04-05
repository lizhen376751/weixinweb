package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.lmk.wxcustomer.api.ApiWxCustomer;
import com.dudu.soa.lmk.wxcustomer.module.WxCustomer;
import org.springframework.stereotype.Service;

/**
 * 联盟用户信息注册的服务
 * Created by lizhen on 2017/4/1.
 */
@Service
public class WxCustomerService {
    /**
     * 引入联盟卡用户注册接口
     */
    @Reference(version = "1.0")
    private ApiWxCustomer apiWxCustomer;

    /**
     * 根据联盟code和车牌号查询是否有此用户
     *
     * @param platenumber 车牌号
     * @param lmcode      联盟code
     * @return WxCustomer客户信息详情
     */
    public WxCustomer getWxCustomer(String platenumber, String lmcode) {
        WxCustomer wxCustomer = new WxCustomer();
        wxCustomer.setCarHaopai(platenumber);
        wxCustomer.setBrandCode(lmcode);
        WxCustomer wxCustomer1 = apiWxCustomer.getWxCustomer(wxCustomer);
        return wxCustomer1;
    }

    /**
     * 修改密码
     *
     * @param platenumber 车牌号
     * @param lmcode      联盟code
     * @param password    密码
     */
    public void updateWxCustomer(String platenumber, String lmcode, String password) {
        WxCustomer wxCustomer = new WxCustomer();
        wxCustomer.setCarHaopai(platenumber);
        wxCustomer.setBrandCode(lmcode);
        wxCustomer.setPassword(password);
        apiWxCustomer.updateWxCustomer(wxCustomer);
    }

    /**
     * 修改用户信息(可直接代替updateWxCustomer的方法)
     *
     * @param wxCustomer 用户信息
     */
    public void updateCustomer(WxCustomer wxCustomer) {
        apiWxCustomer.updateWxCustomer(wxCustomer);
    }

    /**
     * 新增联盟用户信息
     *
     * @param wxCustomer 联盟用户信息
     */
    public void addWxCustomer(WxCustomer wxCustomer) {
        apiWxCustomer.addWxCustomer(wxCustomer);
    }
}
