package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.messagecenter.message.api.ApiSendSms;
import com.dudu.soa.messagecenter.message.module.ParameterEntry;
import com.dudu.soa.weixindubbo.smssend.api.ApiSmsSend;
import com.dudu.soa.weixindubbo.smssend.module.SmsSendLog;
import com.dudu.weixin.util.TestMD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 主要用于1.记录验证码发送记录,2.验证码的验证
 * Created by lizhen on 2017/3/30.
 */
@Service
public class ValidateService {
    /**
     * 引入验证码记录接口
     */
    @Reference(version = "1.0")
    private ApiSmsSend apiSmsSend;
    /**
     * 引入短信发送接口
     */
    @Reference(version = "0.0.1")
    private ApiSendSms apiSendSms;
    /**
     * 引入用户信息的服务(用于修改密码)
     */
    @Autowired
    private WxCustomerService wxCustomerService;

    /**
     * 验证码输入正确性的验证
     *
     * @param platenumber      车牌号
     * @param lmcode           lianmengcode
     * @param mobilephone      手机号
     * @param verificationCode 验证码
     * @return true代表验证码正确, false代表验证码错误
     */
    public Boolean validate(String platenumber, String lmcode, String mobilephone, String verificationCode) {

        //首先验证验证码是否正确?
        SmsSendLog smsSend = new SmsSendLog();
        smsSend.setLmcode(lmcode);
        smsSend.setMobilePhone(mobilephone);
        smsSend.setServiceType("验证码");
        smsSend.setPlateNumber(platenumber);
        SmsSendLog smsSend1 = apiSmsSend.getSmsSend(smsSend);
        //验证是否有发送记录
        if (null != smsSend1) {
            //如果有发送记录,获取验证码
            String identifyingCode = smsSend1.getIdentifyingCode();
            //如果有验证码,验证验证码是否相同
            if (null != identifyingCode && !"".equals(identifyingCode)) {
                //如果验证码相同,则返回true
                if (identifyingCode.equals(verificationCode)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 发送密码,重置密码
     *
     * @param platenumber 车牌号
     * @param lmcode      lianmengcode
     * @param mobilephone 手机号
     */
    public void sendpassWord(String platenumber, String lmcode, String mobilephone) {
        SmsSendLog smsSend = new SmsSendLog();
        smsSend.setLmcode(lmcode);
        smsSend.setMobilePhone(mobilephone);
        smsSend.setPlateNumber(platenumber);
        String radomInt = String.valueOf((new Random().nextInt(999999 - 100000 + 1) + 100000));
        //六位密码的发送
        try {
            //密码的发送
            this.sendvalidate("000000", "重置密码", mobilephone, radomInt);
            //同步的修改用户信息的密码
            wxCustomerService.updateWxCustomer(platenumber, lmcode, TestMD5Util.kL(radomInt));
            //验证码发送成功后进行记录
            smsSend.setServiceType("重置密码");
            smsSend.setIdentifyingCode(radomInt);
            Date date = new Date();
            smsSend.setSendTime(date);
            //添加短信密码的记录
            apiSmsSend.addSmsSend(smsSend);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送验证码并记录
     *
     * @param platenumber 车牌号
     * @param lmcode      lianmengcode
     * @param mobilephone 手机号
     */
    public void addvalidate(String platenumber, String lmcode, String mobilephone) {
        SmsSendLog smsSend = new SmsSendLog();
        smsSend.setLmcode(lmcode);
        smsSend.setMobilePhone(mobilephone);
        smsSend.setPlateNumber(platenumber);
        String radomInt = String.valueOf(new Random().nextInt(9999 - 1000 + 1) + 1000);
        //验证码的发送
        try {
            //验证码的记录
            this.sendvalidate("000001", "验证码", mobilephone, radomInt);
            //验证码发送成功后进行记录
            smsSend.setServiceType("验证码");
            smsSend.setIdentifyingCode(radomInt);
            Date date = new Date();
            smsSend.setSendTime(date);
            //先判断有没有之前的验证码
            SmsSendLog smsSend1 = apiSmsSend.getSmsSend(smsSend);
            //先删除之前的短信验证码
            if (smsSend1 != null) {
                this.deleteValidate(platenumber, lmcode, mobilephone);
            }
            //删除之后添加短信验证码的记录
            apiSmsSend.addSmsSend(smsSend);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除短信验证码的记录
     *
     * @param platenumber 车牌号
     * @param lmcode      lianmengcode
     * @param mobilephone 手机号
     */
    public void deleteValidate(String platenumber, String lmcode, String mobilephone) {
        SmsSendLog smsSend = new SmsSendLog();
        smsSend.setLmcode(lmcode);
        smsSend.setMobilePhone(mobilephone);
        smsSend.setPlateNumber(platenumber);
        smsSend.setServiceType("验证码");
        apiSmsSend.deleteSmsSend(smsSend);
    }

    /**
     * 验证码短信的发送
     *
     * @param mobilephone      手机号
     * @param verificationCode 验证码
     * @param shopcode         lmcode代码
     * @param businessType     业务类型
     */
    public void sendvalidate(String shopcode, String businessType, String mobilephone, String verificationCode) {
        ParameterEntry parameterEntry = new ParameterEntry();
        parameterEntry.setParameter1(verificationCode);
        List list = new ArrayList();
        list.add(mobilephone);
        apiSendSms.sendSMS(shopcode, businessType, list, parameterEntry);
    }

}
