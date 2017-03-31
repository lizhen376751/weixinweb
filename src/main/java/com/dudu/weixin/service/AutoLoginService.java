package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.lmk.wxcustomer.api.ApiWxCustomer;
import com.dudu.soa.lmk.wxcustomer.module.WxCustomer;
import com.dudu.soa.messagecenter.message.api.ApiSendSms;
import com.dudu.soa.weixindubbo.loginlog.api.ApiLogInLog;
import com.dudu.soa.weixindubbo.loginlog.module.LogInLog;
import com.dudu.weixin.util.TestMD5;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/22.
 * 登录.注册类
 * (1.包含验证码的发送与验证
 * 2.密码的重置
 * 3.查询是否具有此用户)
 */
@Service
public class AutoLoginService {


    /**
     * 引入登录历史记录接口
     */
    @Reference(version = "1.0")
    private ApiLogInLog apiLogInLog;
    /**
     * 引入短信发送接口
     */
    @Reference(version = "0.0.1")
    private ApiSendSms apiSendSms;
    /**
     * 引入联盟卡用户注册接口
     */
    @Reference(version = "1.0")
    private ApiWxCustomer apiWxCustomer;
    /**
     * 引入验证码的类
     */
    private ValidateService validateService;

    /**
     * @param openId   微信openId
     * @param shopcode shopcode
     * @return 返回字符串true或者false
     * @Autowired
     */
    //根据OpenId和shopcode查询是否有登记的记录
    public String judgeOpenId(String openId, String shopcode) {
        String carId = "";
        //TODO 后期调用接口
        return carId;

    }

    /**
     * @param code shopcode
     * @return 店铺名称
     * @Autowired
     */
    //根据shopcode查询店铺名称
    public String getDuduShopName(String code) {
        String rtnStr = "";
        //TODO 后期调用接口
        return rtnStr;
    }

    /**
     * 登录
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
            WxCustomer wxCustomer = new WxCustomer();
            wxCustomer.setCarHaopai(platenumber);
            wxCustomer.setBrandCode(lmcode);
            WxCustomer wxCustomer1 = apiWxCustomer.getWxCustomer(wxCustomer);
            //如果查询到此用户数据,进行判断,否则提示登录
            if (null != wxCustomer1) {
                //获取数据的加密密码
                String password1 = wxCustomer1.getPassword();
                //如果密码为空,提示注册
                if (null == password1 || "".equals(password1)) {
                    return "请先注册,在登陆!";
                }
                //如果输入的密码不一致提示密码错误,反之登录成功
                if (!password1.equals(TestMD5.kL(password))) {
                    return "密码输入错误,请重新输入!";
                } else {
                    //1.记录登录记录
                    LogInLog logInLog = new LogInLog();
                    logInLog.setOpenid(openid);
                    //根据openid查询是否有登录记录,如果有只是修改登录记录,没有新增登录记录
                    LogInLog logInLog1 = apiLogInLog.getLogInLog(logInLog);
                    logInLog.setLmcode(lmcode);
                    logInLog.setPlateNumber(platenumber);
                    if (null != logInLog1) {
                        apiLogInLog.updateLogInLog(logInLog);
                    } else {
                        apiLogInLog.addLogInLog(logInLog);
                    }
                    //TODO 2.另外将车牌号,openid,lmcode存如到session里面
                    return "登录成功!";
                }
            }
            return "无此用户,请先注册!";
        } catch (Exception e) {
            e.printStackTrace();
            return "登录失败,请重新登录!";
        }
    }

    /**
     * 注册
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
        Boolean validate = validateService.validate(platenumber, lmcode, mobilephone, verificationCode);
        //如果验证码正确进行注册
        if (validate) {
            //注册之前查询是否有此用户
            WxCustomer wxCustomer = new WxCustomer();

            apiWxCustomer.addWxCustomer(wxCustomer);
        }

        return "注册失败";
    }
//public Boolean isexist(){
//    WxCustomer wxCustomer = new WxCustomer();
//    apiWxCustomer.getWxCustomer();
//}


}
