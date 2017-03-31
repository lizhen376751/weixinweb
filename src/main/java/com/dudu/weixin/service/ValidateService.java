package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.messagecenter.message.api.ApiSendSms;
import com.dudu.soa.messagecenter.message.module.ParameterEntry;
import com.dudu.soa.weixindubbo.smssend.api.ApiSmsSend;
import com.dudu.soa.weixindubbo.smssend.module.SmsSendLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 主要用于1.记录验证码发送记录,2.验证码的验证
 * Created by lizhen on 2017/3/30.
 */

public class ValidateService {
    /**
     * 常量
     */
    private static final int NUM = 9999;
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
     * 记录验证码的发送记录
     *
     * @param platenumber      车牌号
     * @param lmcode           lianmengcode
     * @param mobilephone      手机号
     * @param verificationCode 验证码
     */
    public void addvalidate(String platenumber, String lmcode, String mobilephone, String verificationCode) {
        SmsSendLog smsSend = new SmsSendLog();
        smsSend.setLmcode(lmcode);
        smsSend.setMobilePhone(mobilephone);
        smsSend.setPlateNumber(platenumber);
        String radomInt = String.valueOf(new Random().nextInt(NUM));
        //验证码的发送
        try {
            //验证码的记录
            this.sendvalidate(mobilephone, radomInt);
            //验证码发送成功后进行记录
            smsSend.setServiceType("验证码");
            smsSend.setIdentifyingCode(radomInt);
            Date date = new Date();
            smsSend.setSendTime(date);
            apiSmsSend.addSmsSend(smsSend);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 验证码短信的发送
     *
     * @param mobilephone      手机号
     * @param verificationCode 验证码
     */
    public void sendvalidate(String mobilephone, String verificationCode) {
        //生成随机的四位密码
        ParameterEntry parameterEntry = new ParameterEntry();
        parameterEntry.setParameter1(verificationCode);
        String shopcode = "000001"; //因为用我们自己的阿里账号所以shopcode固定
        String businessType = "验证码"; //业务类型固定
        List list = new ArrayList();
        list.add(mobilephone);
        apiSendSms.sendSMS(shopcode, businessType, list, parameterEntry);
    }

}
