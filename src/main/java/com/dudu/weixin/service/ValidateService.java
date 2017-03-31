package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.messagecenter.message.api.ApiSendSms;
import com.dudu.soa.weixindubbo.smssend.api.ApiSmsSend;
import com.dudu.soa.weixindubbo.smssend.module.SmsSend;

import java.util.ArrayList;
import java.util.Random;

/**
 * 主要用于1.记录验证码发送记录,2.验证码的验证
 * Created by lizhen on 2017/3/30.
 */

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
     *验证码的验证
     * @param platenumber 车牌号
     * @param lmcode lianmengcode
     * @param mobilephone 手机号
     * @param verificationCode 验证码
     * @return true代表验证码正确,false代表验证码错误
     */
    public Boolean validate(String platenumber, String lmcode, String mobilephone, String verificationCode){

        //首先验证验证码是否正确?
        SmsSend smsSend = new SmsSend();
        smsSend.setLmcode(lmcode);
        smsSend.setMobilePhone(mobilephone);
        smsSend.setPlateNumber(platenumber);
        SmsSend smsSend1 = apiSmsSend.getSmsSend(smsSend);
        //验证是否有发送记录
        if (null != smsSend1) {
            //如果有发送记录,获取验证码
            String identifyingCode = smsSend1.getIdentifyingCode();
            //如果有验证码,验证验证码是否相同
            if (null != identifyingCode && !"".equals(identifyingCode)) {
                //如果验证码相同,则注册成功
                if (identifyingCode.equals(verificationCode)){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     *验证码的发送
     * @param platenumber 车牌号
     * @param lmcode lianmengcode
     * @param mobilephone 手机号
     * @param verificationCode 验证码
     *
     */
    public void addvalidate(String platenumber, String lmcode, String mobilephone, String verificationCode){
        SmsSend smsSend = new SmsSend();
        smsSend.setLmcode(lmcode);
        smsSend.setMobilePhone(mobilephone);
        smsSend.setPlateNumber(platenumber);
        String radomInt = String.valueOf(new Random().nextInt(9999));
        SmsSend smsSend2  = new SmsSend();
        String shopcode = "000000"; //因为用我们自己的阿里账号所以shopcode固定
        String businessType = "重置密码"; //业务类型固定

    }
//    /短信发送,重置密码
//    public void sendSms(String strUserId,String mobile){
//        SmsSend smsSend  = new SmsSend();
//        String shopcode = "000000"; //因为用我们自己的阿里账号所以shopcode固定
//        String businessType = "重置密码"; //业务类型固定
//        List list = new ArrayList();
//        list.add(mobile);
//        ParameterEntry parameterEntry = new ParameterEntry();
//        String radomInt = String.valueOf(new Random().nextInt(999999));
//        String strNewPassword = MD5.toMD5(radomInt);
//
//        parameterEntry.setParameter1(radomInt);
//        smsSend.sendSms(shopcode, businessType, list, parameterEntry);
//        try {
//            DBBase dBBase = new DBBase();
//            StringBuffer sbSql = new StringBuffer();
//            sbSql.append("UPDATE DDHG_Manage_user SET password = '")
//                    .append(strNewPassword)
//                    .append("',updatetime = getdate() ")
//                    .append(" WHERE loadid = '")
//                    .append(strUserId)
//                    .append("'");
//            dBBase.update(sbSql.toString());
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//    }

}
