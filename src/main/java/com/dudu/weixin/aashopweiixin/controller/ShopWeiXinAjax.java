package com.dudu.weixin.aashopweiixin.controller;

import com.dudu.weixin.aashopweiixin.service.ShopWeixinLogin;
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
     * @param request 请求
     * @return 对象
     */
    @ResponseBody
    @RequestMapping(value = "/shopAjax", method = RequestMethod.POST)
    public Object commonAjax(HttpServletRequest request) {
        Object shopcode = httpSession.getAttribute("shopcode");
        String businessType = request.getParameter("businessType");
        if ("login".equals(businessType)) {
            String platenumber = request.getParameter("platenumber");
            String password = request.getParameter("password");
            String checklogin = shopWeixinLogin.checklogin(shopcode.toString(), platenumber, password, request);
            return checklogin;
        }
        return null;
    }
}
