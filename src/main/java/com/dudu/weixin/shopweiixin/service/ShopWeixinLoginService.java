package com.dudu.weixin.shopweiixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.customercenter.customer.api.ApiCustomerInfo;
import com.dudu.soa.customercenter.customer.module.CustomerInfo;
import com.dudu.soa.customercenter.customer.module.CustomerInfoParam;
import com.dudu.soa.weixindubbo.shopinfo.api.ApiShopInfo;
import com.dudu.soa.weixindubbo.shopweixinuser.api.ApiShopWeixinUser;
import com.dudu.soa.weixindubbo.shopweixinuser.module.ShopWeixinUser;
import com.dudu.soa.weixindubbo.weixin.http.api.ApiAllWeiXiRequest;
import com.dudu.weixin.service.LogInLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 车管家的登录
 * Created by lizhen on 2017/5/2.
 */
@Service
public class ShopWeixinLoginService {
    /**
     * 引入店管家的客户中心接口
     */
    @Reference(version = "0.0.1")
    private ApiCustomerInfo apiCustomerInfo;
    /**
     * 引入店管家微信的用户中心的接口
     */
    @Reference(version = "1.0")
    private ApiShopWeixinUser apishopWeixinUser;
    /**
     * 引入微信通讯相关的基础接口
     */
    @Reference(version = "1.0", timeout = 300000)
    private ApiAllWeiXiRequest apiAllWeiXiRequest;
    /**
     * 引入查询店铺信息接口
     */
    @Reference(version = "1.0")
    private ApiShopInfo apishopInfo;
    /**
     * 引入登录记录的服务
     */
    @Autowired
    private LogInLogService logInLogService;

    /**
     * @param shopcode 店铺编码
     * @param request  请求
     * @return 0提示用户名或者密码错误, 返回1表示登录成功, 返回2网络错误提示客户重新登录
     */
    public String checklogin(String shopcode, HttpServletRequest request) {
        String plateNumber = request.getParameter("platenumber").toUpperCase(); //车牌号
        String password = request.getParameter("password"); //密码

        HttpSession session = request.getSession();
        String openId = session.getAttribute("openId").toString();
        String nickname = session.getAttribute("nickname").toString();
        //先去baidumap的客户中心查询是否有此用户
        ArrayList<ShopWeixinUser> shopWeixinUsers = this.queryShopWeixinUser(shopcode, plateNumber);
        //如果有此用户进行以下判断,没有的话去店管家的客户中心查询
        if (null != shopWeixinUsers && shopWeixinUsers.size() > 0) {
            //获取此用户的基础信息
            ShopWeixinUser shopWeixinUser = shopWeixinUsers.get(0);
            //获取密码
            String userPass = shopWeixinUser.getUserPass();
            //判断密码是否为空
            if (null != userPass && !"".equals(userPass)) {
                //如果密码相等,登录成功
                if (userPass.equals(password)) {
                    //返回1表示登录成功,增加登录记录
                    Integer integer = logInLogService.addLogInLog(plateNumber, shopcode, openId, nickname);
                    String s = successORerror(integer, shopcode, plateNumber, request);
                    return s;

                } else {
                    //提示用户名或者密码错误
                    return "0";
                }
            } else {
                //密码为空,判断手机号与密码是否相等
                if (shopWeixinUser.getMobile().equals(password)) {
                    //返回1表示登录成功,增加登录记录
                    Integer integer = logInLogService.addLogInLog(plateNumber, shopcode, openId, nickname);
                    String s = successORerror(integer, shopcode, plateNumber, request);
                    return s;
                } else {
                    //提示用户名或者密码错误
                    return "0";
                }
            }
        } else {
            //店管家的客户中心查询是否有此用户
            List<CustomerInfo> customerInfos = this.queryCustomerList(shopcode, plateNumber, password);
            if (null != customerInfos && customerInfos.size() > 0) {
                //将店管家查询到的数据存入到百度map的客户中心
                CustomerInfo customerInfo = customerInfos.get(0);
                ShopWeixinUser shopWeixinUser = this.getShopWeixinUser(customerInfo, openId, nickname);
                Integer integer = apishopWeixinUser.addShopWeixinUser(shopWeixinUser);
                Integer integer1 = logInLogService.addLogInLog(plateNumber, shopcode, openId, nickname);
                //如果数据小于0则没有保存成功,返回2提示客户重新登录
                String s = successORerror(integer, shopcode, plateNumber, request);
                return s;
            } else {
                //没有此用户提示用户名或者密码错误
                return "0";
            }


        }
    }

    /**
     * 根据店铺编码和车牌号码查询店管家微信是否有此用户
     *
     * @param shopcode    店铺编码
     * @param plateNumber 车牌号码
     * @return 用户的集合
     */
    public ArrayList<ShopWeixinUser> queryShopWeixinUser(String shopcode, String plateNumber) {
        ShopWeixinUser shopWeixinUser = new ShopWeixinUser();
        shopWeixinUser.setShopcode(shopcode).setUserName(plateNumber);
        ArrayList<ShopWeixinUser> shopWeixinUsers = apishopWeixinUser.queryShopWeixinUser(shopWeixinUser);
        if (null != shopWeixinUsers && shopWeixinUsers.size() > 0) {
            return shopWeixinUsers;
        }
        return null;
    }

    /**
     * 车管家微信的用户登录时进行验证
     *
     * @param mainShopCode 主店铺id
     * @param plateNumber  车牌号
     * @param mobilePhone  手机号
     * @return 客户信息的集合
     */
    public List<CustomerInfo> queryCustomerList(String mainShopCode, String plateNumber, String mobilePhone) {
        CustomerInfoParam customerInfoParam = new CustomerInfoParam()
                .setMainShopCode(mainShopCode)
                .setPlateNumber(plateNumber)
                .setMobilePhone(mobilePhone);
        List<CustomerInfo> customerInfos = apiCustomerInfo.queryCustomerList(customerInfoParam);
        return customerInfos;
    }

    /**
     * 组装店管家微信的客户中心
     *
     * @param customerInfo 店管家的用户中心
     * @param openid       微信的openid
     * @param nickname     微信别名
     * @return 店管家的用户信息
     */
    public ShopWeixinUser getShopWeixinUser(CustomerInfo customerInfo, String openid, String nickname) {
        ShopWeixinUser shopWeixinUser = new ShopWeixinUser();
        shopWeixinUser.setUserName(customerInfo.getPlateNumber());
        shopWeixinUser.setShopcode(customerInfo.getShopCode());
        shopWeixinUser.setMobile(customerInfo.getMobilePhone());
        shopWeixinUser.setAddress(customerInfo.getCustomerAddress());
        shopWeixinUser.setBrand(customerInfo.getCarBrand());
        shopWeixinUser.setEngine(customerInfo.getEngineNumber());
        shopWeixinUser.setIDCode(customerInfo.getId().toString());
        shopWeixinUser.setSerise(customerInfo.getCarSeries());
        Integer sex = customerInfo.getSex();
        if (null != sex) {
            shopWeixinUser.setXingBie(sex.toString());
        }
        shopWeixinUser.setXingMing(customerInfo.getCustomerName());
        shopWeixinUser.setOpenId(openid);
        shopWeixinUser.setNickname(nickname);
        return shopWeixinUser;
    }

    /**
     * 数据操作之后进行判断
     *
     * @param integer     数据操作条数
     * @param shopcode    店铺编码
     * @param plateNumber 车牌号码
     * @param request     请求
     * @return 字符串
     */
    public String successORerror(Integer integer, String shopcode, String plateNumber, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (integer > 0) {
            //登录成功将shopcode和车牌号码存入到session里面
            session.setAttribute("shopcode", shopcode);
            session.setAttribute("plateNumber", plateNumber);
            return "1";
        } else {
            //返回2提示客户重新登录
            return "2";
        }
    }
}
