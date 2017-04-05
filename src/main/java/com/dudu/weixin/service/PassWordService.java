package com.dudu.weixin.service;


import com.dudu.soa.lmk.wxcustomer.module.WxCustomer;
import com.dudu.weixin.util.TestMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 密码修改,密码找回
 * Created by lizhen on 2017/4/1.
 */
@Service
public class PassWordService {
    /**
     * 引入验证码发送的服务
     */
    @Autowired
    private ValidateService validateService;
    /**
     * 引入联盟用户信息注册的服务
     */
    @Autowired
    private WxCustomerService wxCustomerService;

    /**
     * 密码修改
     *
     * @param platenumber 车牌号
     * @param lmcode      联盟code
     * @param oldpassword 原密码
     * @param newpassword 新密码
     * @return 0代表密码输入错误1代表修改成功2代表修改失败
     */
    public String updatePassword(String platenumber, String lmcode, String oldpassword, String newpassword) {
        //车牌号填写之后,根据车牌号和lmcode查询是否有此用户
        WxCustomer wxCustomer1 = wxCustomerService.getWxCustomer(platenumber, lmcode);
        //判断是否有此用户信息
        if (null != wxCustomer1) {
            //有用户信息的情况,判断是否有密码
            String password = wxCustomer1.getPassword();
            if (null != password && !"".equals(password)) {
                if (!password.equals(TestMD5.kL(oldpassword))) {
                    //密码输入错误
                    return "0";
                } else {
                    //密码匹配成功,修改密码
                    WxCustomer wxCustomer = new WxCustomer();
                    wxCustomer.setCarHaopai(platenumber);
                    wxCustomer.setPassword(TestMD5.kL(newpassword));
                    wxCustomer.setBrandCode(lmcode);
                    wxCustomerService.updateCustomer(wxCustomer);
                    //密码输入正确
                    return "1";
                }
            }
        }
        //密码修改失败
        return "2";
    }

    /**
     * 找回密码前的用户验证
     *
     * @param platenumber 车牌号
     * @param lmcode      联盟code
     * @param phoneMobile 手机号
     * @return 字符串
     */
    public String getBackPassword(String platenumber, String lmcode, String phoneMobile) {
        WxCustomer wxCustomer1 = wxCustomerService.getWxCustomer(platenumber, lmcode);
        if (null != wxCustomer1) {
            //0:无此用户,提示注册
            return "0";
        } else {
            String customerMobile = wxCustomer1.getCustomerMobile();
            if (phoneMobile != customerMobile && !phoneMobile.equals(customerMobile)) {
                //手机号码与车牌号不匹配
                return "1";
            } else {
                //表示匹配成功可进行以下操作
                return "2";
            }
        }
    }

    /**
     * 找回密码的提交
     *
     * @param platenumber      车牌号
     * @param lmcode           联盟code
     * @param verificationCode 验证码
     * @param mobilephone      手机号
     * @return 字符串
     */
    public String register(String platenumber, String lmcode, String mobilephone, String verificationCode) {
        //首先验证验证码是否输入正确
        Boolean validate = validateService.validate(platenumber, lmcode, mobilephone, verificationCode);
        //如果验证码正确进行提交
        if (validate) {
            //说明验证码正确
            return "3";
        } else {
            //返回4 验证码:输入错误
            return "4";
        }
    }


}
