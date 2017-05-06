package com.dudu.weixin.shopweiixin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 店管家所有的页面跳转
 * Created by lizhen on 2017/5/2.
 */

@Controller
@RequestMapping("/")
public class ShopWeiXinControl {
    /**
     * 日志打印
     */
    private static Logger logprint = LoggerFactory.getLogger(ShopWeiXinControl.class);
    /**
     * session
     */
    @Autowired
    private HttpSession httpSession;

    /**
     * 所有店管家微信菜单的页面的跳转
     *
     * @param request 请求
     * @param model   模版参数
     * @return 字符串路径
     */
    @RequestMapping(value = "shopweixinMenuServlet", method = RequestMethod.GET)
    public String shopWeiXinMenuPageJump(HttpServletRequest request, Model model) {
        String strWxShopcode = request.getParameter("shopcode");
        String flagStr = strWxShopcode.split("_")[1]; //页面跳转判断
        String shopcode = strWxShopcode.split("_")[0]; //联盟code
        if ("AHIInfo".equals(flagStr)) {
            return "/ahi/AHIxiangqing.jsp"; //AHI指数
        } else if ("xiaofeijilu".equals(flagStr)) {
            return "/shopxiaofeijilu/xiaofeijilu.jsp"; //消费记录
        }
        return null;
    }

    /**
     * 店管家微信项目内部的跳转
     *
     * @param request 请求
     * @param model   模板
     * @return 字符串的路径跳转
     */
    @RequestMapping(value = "shopweixinServlet", method = RequestMethod.GET)
    public String shopWeiXinPageJump(HttpServletRequest request, Model model) {
        String serviceType = request.getParameter("serviceType");
        if ("login".equals(serviceType)) {
            return "/shoplogin/shoplogin.jsp"; //登录页面
        } else if ("AHIInfo".equals(serviceType)) {
            //TODO 登录在成功之后暂时跳转至ahi,后期换成个人中心
            return "/ahi/AHIxiangqing.jsp"; //AHI指数
        }
        return null;
    }
}
