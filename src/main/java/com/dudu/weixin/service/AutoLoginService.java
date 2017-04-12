package com.dudu.weixin.service;

import com.dudu.soa.lmk.wxcustomer.module.WxCustomer;
import com.dudu.weixin.util.TestMD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/22.
 * 登录.注册
 */
@Service
public class AutoLoginService {

    /**
     * 引入验证码的服务
     */
    @Autowired
    private ValidateService validateService;
    /**
     * 引入联盟用户信息注册的服务
     */
    @Autowired
    private WxCustomerService wxCustomerService;
    /**
     * 引入登录记录的服务
     */
    @Autowired
    private LogInLogService logInLogService;


    /**
     * 注册和登录:车牌号输入之后发送请求
     *
     * @param platenumber 车牌号
     * @param lmcode      联盟code
     * @return 字符串 0
     */
    public String checkInfo(String platenumber, String lmcode) {
        //车牌号填写之后,根据车牌号和lmcode查询是否有此用户
        WxCustomer wxCustomer1 = wxCustomerService.getWxCustomer(platenumber, lmcode);
        //判断是否有此用户信息
        if (null != wxCustomer1) {
            //有用户信息的情况,判断是否有密码
            String password = wxCustomer1.getPassword();
            if (null != password && !"".equals(password)) {
                //如果有密码直接返回2,代表直接登录
                return "2";
            } else {
                //如果没有密码,将发送密码
                return "1";
            }

        } else {
            //没有用户信息可进行注册操作,如果登录页面提示注册
            return "0";
        }
    }

    /**
     * 点击登录按钮
     *
     * @param platenumber 车牌号
     * @param lmcode      联盟code
     * @param password    密码
     * @param openid      微信id
     * @return 是否正确
     */
    public String login(String platenumber, String lmcode, String password, String openid) {
        /**
         * 根据车牌号和联盟code查询联盟用户表是否有数据
         */
        try {
            WxCustomer wxCustomer1 = wxCustomerService.getWxCustomer(platenumber, lmcode);
            //如果查询到此用户数据,进行判断,否则提示登录
            if (null != wxCustomer1) {
                //获取数据的加密密码
                String password1 = wxCustomer1.getPassword();
                //如果密码为空,提示注册
                if (null == password1 || "".equals(password1)) {
                    return "1";
                }
                //如果输入的密码不一致提示密码错误,反之登录成功
                if (!password1.equals(TestMD5Util.kL(password))) {
                    return "3";
                } else {
                    //1.记录登录记录
                    logInLogService.addLogInLog(platenumber, lmcode, openid);
                    //TODO 2.另外将车牌号,openid,lmcode存如到session里面
                    return "4";
                }
            }
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "5";
        }
    }


    /**
     * 点击注册按钮
     *
     * @param platenumber      车牌号
     * @param lmcode           联盟code
     * @param password         密码
     * @param openid           微信id
     * @param mobilephone      手机号
     * @param verificationCode 验证码
     * @return 字符串
     */
    public String register(String platenumber, String lmcode, String password, String openid, String mobilephone, String verificationCode) {
        //首先验证验证码是否输入正确
        Boolean validate = validateService.validate(platenumber, lmcode, mobilephone, verificationCode);
        //如果验证码正确进行注册
        if (validate) {
            //新增注册的信息
            WxCustomer wxCustomer = new WxCustomer();
            wxCustomer.setBrandCode(lmcode);
            wxCustomer.setCarHaopai(platenumber);
            wxCustomer.setCustomerMobile(mobilephone);
            wxCustomer.setPassword(TestMD5Util.kL(password));
            wxCustomerService.addWxCustomer(wxCustomer);
            //先根据openid判断之前有没有登录的信息
            logInLogService.addLogInLog(platenumber, lmcode, openid);

            //返回3注册成功
            return "3";
        } else {
            //返回4验证码:输入错误
            return "4";
        }
    }



}
