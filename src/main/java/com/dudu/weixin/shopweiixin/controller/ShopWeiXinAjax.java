package com.dudu.weixin.shopweiixin.controller;

import com.dudu.weixin.shopweiixin.service.ShopBaoYangTiXing;
import com.dudu.weixin.shopweiixin.service.ShopWeixinLogin;
import com.dudu.weixin.shopweiixin.service.ShopXiaoFeiJiLu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 所有的店管家微信的Ajax入口
 * Created by lizhen on 2017/5/2.
 */
@Controller
@RequestMapping("/")
public class ShopWeiXinAjax {
    /**
     * logprint 日志打印
     */
    private static Logger logprint = LoggerFactory.getLogger(ShopWeiXinAjax.class);
    /**
     * 引入登录的服务
     */
    @Autowired
    private ShopWeixinLogin shopWeixinLogin;
    /**
     * 引入session
     */
    @Autowired
    private HttpSession httpSession;
    /**
     * 引入消费记录服务
     */
    @Autowired
    private ShopXiaoFeiJiLu shopXiaoFeiJiLu;
    /**
     * 引入保养提醒的服务
     */
    @Autowired
    private ShopBaoYangTiXing shopBaoYangTiXing;

    /**
     * @param request 请求
     * @return 对象
     */
    @ResponseBody
    @RequestMapping(value = "shopAjax", method = RequestMethod.POST)
    public Object commonAjax(HttpServletRequest request) {
        String shopcode = (String) httpSession.getAttribute("shopcode");
        String platenumber = (String) httpSession.getAttribute("plateNumber");
        String businessType = request.getParameter("businessType");
        if ("login".equals(businessType)) { //登录页面
            platenumber = request.getParameter("platenumber");
            String password = request.getParameter("password");
            String checklogin = shopWeixinLogin.checklogin(shopcode, platenumber, password, request);
            return checklogin;
        } else if ("xiaofeijilu".equals(businessType)) { //消费记录页面
            return shopXiaoFeiJiLu.queryXiaoFeiJiLu(shopcode, platenumber);
        } else if ("baoYangTiXingList".equals(businessType)) { //保养提醒页面
            return shopBaoYangTiXing.queryBaoYangTiXing(shopcode, platenumber);
        }
        return null;
    }
}
